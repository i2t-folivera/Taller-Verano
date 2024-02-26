package com.nivelacion.taller.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nivelacion.taller.dtos.UsuarioDTO;
import com.nivelacion.taller.enums.Role;
import com.nivelacion.taller.mappers.UsuarioMapper;
import com.nivelacion.taller.models.Usuario;
import com.nivelacion.taller.repository.UsuarioRepository;
import com.nivelacion.taller.services.UsuarioService;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UsuarioServicesImpl implements UsuarioService, UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        if (usuario == null) {
            log.error("Usuario no encontrado en la BD");
            throw new UsernameNotFoundException("usuario no encontrado en la bd");
        } else {
            log.info("El usuario encontrado en la BD es: {}", correo);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        usuario.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(usuario.getCorreo(), usuario.getContraseña(),
                authorities);
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        log.info("Registro nuevo usuario {} en la BD" + usuario.getCorreo());
        usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioDTO registerUserLoginDTO(UsuarioDTO usuarioDTO) throws Exception {
        Boolean existeCorreo = this.usuarioRepository.existsByCorreo(usuarioDTO.getCorreo());
        if (!existeCorreo) {
            Usuario newUser = usuarioMapper.dto2Model(usuarioDTO);
            newUser.setContraseña(passwordEncoder.encode(newUser.getContraseña()));// encripto contraseña
            // Obtener el rol del UserDTO o establecer por defecto ADMIN si no se
            // proporciona
            // String rol = (usuarioDTO.getRoles() != null &&
            // !usuarioDTO.getRoles().isEmpty()) ? usuarioDTO.getRoles():
            // usuarioDTO.setRoles(["USER_ADMIN"]);
            // newUser.setRoles(rol);

            Collection<Role> roles = usuarioDTO.getRoles();

            if (roles == null || roles.isEmpty()) {
                // Si el usuario no tiene roles asignados, estableceremos "ROLE_USER" por
                // defecto
                roles = new HashSet<>();
                roles.add(Role.ROLE_USER);
            }
            newUser.setRoles(roles);
            this.usuarioRepository.save(newUser);

            return usuarioDTO;
        } else {
            throw new Exception("Correo existente, elija otro por favor.");
        }
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Registrando nuevo rol {} en la BD " + role.getName());
        return role;
    }

    @Override
    public void addRoleToUsuario(String correo, Role role) {
        log.info("Agregando rol {} al usuario {} ", role.getName(), correo);
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        usuario.getRoles().add(role);
        usuarioRepository.save(usuario);

    }

    @Override
    public Usuario getUsuario(String correo) {
        log.info("Obteniendo usuario {} ", correo);
        return usuarioRepository.findByCorreo(correo);
    }

    @Override
    public List<Usuario> getUsuarios() {
        log.info("Obteniendo todos los usuarios");
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario updateUsuario(Usuario usuario, UsuarioDTO usuarioDTO) {
        // Llama al método userEntityRefreshValues para actualizar el usuario con la
        // información del DTO
        usuarioMapper.userEntityRefreshValues(usuario, usuarioDTO);

        return usuario;
    }

}
