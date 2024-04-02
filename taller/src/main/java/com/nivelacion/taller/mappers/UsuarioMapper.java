package com.nivelacion.taller.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.nivelacion.taller.dtos.UsuarioDTO;
import com.nivelacion.taller.models.Usuario;

@Component
public class UsuarioMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioDTO originalToDTO(Usuario usuario) {
        UsuarioDTO dtoUser = new UsuarioDTO();
        dtoUser.setId(usuario.getId());
        dtoUser.setNombre(usuario.getNombre());
        dtoUser.setApellido(usuario.getApellido());
        dtoUser.setMail(usuario.getMail());
        dtoUser.setContrasenia(usuario.getContrasenia());
        dtoUser.setRoles(usuario.getRoles());
        return dtoUser;
    }

    public Usuario dto2Model(UsuarioDTO userDTO) {
        Usuario newUser = new Usuario();
        newUser.setId(userDTO.getId());
        newUser.setNombre(userDTO.getNombre());
        newUser.setApellido(userDTO.getApellido());
        newUser.setMail(userDTO.getMail());
        newUser.setContrasenia(userDTO.getContrasenia());
        newUser.setRoles(userDTO.getRoles());
        return newUser;
    }

    public List<UsuarioDTO> modelUserToUserDTO(List<Usuario> usuariosList) {
        List<UsuarioDTO> dtoList = usuariosList
                .stream()
                .map(i -> this.originalToDTO(i))
                .collect(Collectors.toList());
        return dtoList;
    }

    public void userEntityRefreshValues(Usuario userEntity, UsuarioDTO dto) {
        if (dto.getMail() != null && dto.getMail().isBlank()) {
            userEntity.setMail(dto.getMail());
        }
        if (dto.getNombre() != null && !dto.getNombre().isBlank()) {
            userEntity.setNombre(dto.getNombre());
        }
        if (dto.getApellido() != null && !dto.getApellido().isBlank()) {
            userEntity.setApellido(dto.getApellido());
        }
        if (dto.getMail() != null && !dto.getMail().isBlank()) {
            userEntity.setMail(dto.getMail());
        }
        // if (dto.getContrasenia() != null && !dto.getContrasenia().isBlank()) {
        // userEntity.setContrasenia(dto.getContrasenia());
        // }
        if (dto.getContrasenia() != null && !dto.getContrasenia().isBlank()) {
            // Encriptar la nueva contraseña antes de establecerla en el usuario
            userEntity.setContrasenia(passwordEncoder.encode(dto.getContrasenia()));
        }
        userEntity.setRoles(dto.getRoles());
    }

    // public UsuarioLoginDTO userToDTO(Usuario usuario) {
    // UsuarioLoginDTO usuarioLoginDTO = new UsuarioLoginDTO();
    // usuarioLoginDTO.setUsername(usuario.getMail());
    // usuarioLoginDTO.setPassword(usuario.getContrasenia());
    // return usuarioLoginDTO;
    // }
}