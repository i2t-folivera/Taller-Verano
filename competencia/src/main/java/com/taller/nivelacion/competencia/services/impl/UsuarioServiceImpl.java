package com.taller.nivelacion.competencia.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.taller.nivelacion.competencia.dtos.UsuarioDTO;
import com.taller.nivelacion.competencia.dtos.UsuarioLoginDTO;
import com.taller.nivelacion.competencia.exceptions.InvalidUserException;
import com.taller.nivelacion.competencia.mappers.UsuarioMapper;
import com.taller.nivelacion.competencia.models.Role;
import com.taller.nivelacion.competencia.models.Usuario;
import com.taller.nivelacion.competencia.repository.RoleRepository;
import com.taller.nivelacion.competencia.repository.UsuarioRepository;
import com.taller.nivelacion.competencia.services.UsuarioService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.core.Authentication;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    @Override
    public List<UsuarioDTO> getUsersList() {
        List<Usuario> model = usuarioRepository.findAll();
        List<UsuarioDTO> dtoList = usuarioMapper.modelUserToUserDTO(model);
        return dtoList;
    }

    @Override
    public Usuario findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    @Override
    public UsuarioDTO registerUserLoginDTO(UsuarioDTO usuarioDTO) throws Exception {
        Boolean existeCorreo = this.usuarioRepository.existsByCorreo(usuarioDTO.getCorreo());
        if (!existeCorreo) {
            Usuario newUser = usuarioMapper.dto2Model(usuarioDTO);
            newUser.setContraseña(passwordEncoder.encode(newUser.getContraseña()));// encripto contraseña
            // Obtener el rol del UserDTO o establecer por defecto ADMIN si no se
            // proporciona
            // String rol = (usuarioDTO.getRol() != null && !usuarioDTO.getRol().isEmpty())
            // ? usuarioDTO.getRol()
            // : "ADMIN";
            // newUser.setRol(rol);
            this.usuarioRepository.save(newUser);

            return usuarioDTO;
        } else {
            throw new Exception("Correo existente, elija otro por favor.");
        }
    }

    @Override
    public UsuarioLoginDTO authUser(String correo, String password) throws InvalidUserException {
        if (!existsByEmail(correo)) {
            throw new InvalidUserException("El correo no existe");
        }
        Usuario usuario = findByCorreo(correo);
        if (!passwordEncoder.matches(password, usuario.getContraseña())) {
            throw new InvalidUserException("La contraseña no es correcta");
        }
        UsuarioLoginDTO usuarioLoginDTO = userToDTO(usuario);
        injectUserInSecurityContext(correo, password);
        return usuarioLoginDTO;
    }

    @Override
    public UsuarioLoginDTO userToDTO(Usuario usuario) {
        return usuarioMapper.userToDTO(usuario);
    }

    @Override
    public Boolean existsByEmail(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

    private void injectUserInSecurityContext(String correo, String password) {
        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(correo, password));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    // rolear usuario

    @Override
    public void addRoleToUsuario(String correo, String roleName) {
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        Role role = roleRepository.findByName(roleName);
        usuario.getRoles().add(role);
    }

    @Override
    public Usuario getUsuario(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    @Override
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

}
