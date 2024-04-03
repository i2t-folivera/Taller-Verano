package com.nivelacion.taller.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByMail(mail);
        if (usuario == null) {
            log.error("Usuario no encontrado en la BD: {}", mail);
            throw new UsernameNotFoundException("usuario no encontrado en la bd");
        } else {
            log.info("El usuario encontrado en la BD es: {}", mail);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        // AGREGAR ROL ADMIN
        // if (usuario.getRoles() == null || usuario.getRoles().isEmpty()) {
        // // Si el usuario no tiene roles asignados, asignamos automáticamente el rol
        // // ADMIN
        // usuario.setRoles(Collections.singleton(Role.ROLE_ADMIN));
        // usuarioRepository.save(usuario);
        // }

        usuario.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(usuario.getMail(), usuario.getContrasenia(),
                authorities);
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        log.info("Registro nuevo usuario {} en la BD" + usuario.getMail());
        usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
        return usuarioRepository.save(usuario);
    }

    // @Override
    // public UsuarioDTO registerUserLoginDTO(UsuarioDTO usuarioDTO) throws
    // Exception {
    // Boolean existeMail =
    // this.usuarioRepository.existsByMail(usuarioDTO.getMail());
    // if (!existeMail) {
    // Usuario newUser = usuarioMapper.dto2Model(usuarioDTO);
    // newUser.setContrasenia(passwordEncoder.encode(newUser.getContrasenia()));//
    // encripto contrasenia
    // // Obtener el rol del UserDTO o establecer por defecto ADMIN si no se
    // // proporciona
    // // String rol = (usuarioDTO.getRoles() != null &&
    // // !usuarioDTO.getRoles().isEmpty()) ? usuarioDTO.getRoles():
    // // usuarioDTO.setRoles(["USER_ADMIN"]);
    // // newUser.setRoles(rol);

    // Collection<Role> roles = usuarioDTO.getRoles();

    // if (roles == null || roles.isEmpty()) {
    // // Si el usuario no tiene roles asignados, estableceremos "ROLE_USER" por
    // // defecto
    // roles = new HashSet<>();
    // roles.add(Role.ROLE_ADMIN);
    // }
    // newUser.setRoles(roles);

    // // Guardar el nuevo usuario en la base de datos
    // this.usuarioRepository.save(newUser);

    // // return usuarioDTO;
    // // Devolver el DTO del nuevo usuario creado
    // return usuarioMapper.originalToDTO(newUser);
    // } else {
    // throw new Exception("Mail existente, elija otro por favor.");
    // }
    // }

    @Override
    public UsuarioDTO registerUserLoginDTO(UsuarioDTO usuarioDTO) throws Exception {
        Boolean existeMail = this.usuarioRepository.existsByMail(usuarioDTO.getMail());
        if (!existeMail) {
            Usuario newUser = usuarioMapper.dto2Model(usuarioDTO);
            newUser.setContrasenia(passwordEncoder.encode(newUser.getContrasenia())); // encriptar contraseña

            Collection<Role> roles = usuarioDTO.getRoles();

            if (roles == null || roles.isEmpty()) {
                // Si el usuario no tiene roles asignados, estableceremos "ROLE_USER" por
                // defecto
                roles = new HashSet<>();
                roles.add(Role.ROLE_USER);
            }
            newUser.setRoles(roles);

            // Guardar el nuevo usuario en la base de datos
            this.usuarioRepository.save(newUser);

            // Devolver el DTO del nuevo usuario creado
            return usuarioMapper.originalToDTO(newUser);
        } else {
            throw new Exception("Mail existente, elija otro por favor.");
        }
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Registrando nuevo rol {} en la BD " + role.getName());
        return role;
    }

    @Override
    public void addRoleToUsuario(String mail, Role role) {
        log.info("Agregando rol {} al usuario {} ", role.getName(), mail);
        Usuario usuario = usuarioRepository.findByMail(mail);
        usuario.getRoles().add(role);
        usuarioRepository.save(usuario);

    }

    @Override
    public Usuario getUsuario(String mail) {
        log.info("Obteniendo usuario {} ", mail);
        return usuarioRepository.findByMail(mail);
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
