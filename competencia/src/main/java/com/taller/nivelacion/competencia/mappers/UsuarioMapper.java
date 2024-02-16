package com.taller.nivelacion.competencia.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.taller.nivelacion.competencia.dtos.UsuarioDTO;
import com.taller.nivelacion.competencia.dtos.UsuarioLoginDTO;
import com.taller.nivelacion.competencia.models.Usuario;

@Component
public class UsuarioMapper {

    public UsuarioDTO originalToDTO(Usuario usuario) {
        UsuarioDTO dtoUser = new UsuarioDTO();
        dtoUser.setNombre(usuario.getNombre());
        dtoUser.setApellido(usuario.getApellido());
        dtoUser.setCorreo(usuario.getCorreo());
        dtoUser.setRol(usuario.getRol());
        dtoUser.setContraseña(usuario.getContraseña());
        return dtoUser;
    }

    public Usuario dto2Model(UsuarioDTO userDTO) {
        Usuario newUser = new Usuario();
        newUser.setNombre(userDTO.getNombre());
        newUser.setApellido(userDTO.getApellido());
        newUser.setCorreo(userDTO.getCorreo());
        newUser.setRol(userDTO.getRol());
        newUser.setContraseña(userDTO.getContraseña());
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
        if (dto.getCorreo() != null && dto.getCorreo().isBlank()) {
            userEntity.setCorreo(dto.getCorreo());
        }
        if (dto.getNombre() != null && !dto.getNombre().isBlank()) {
            userEntity.setNombre(dto.getNombre());
        }
        if (dto.getApellido() != null && !dto.getApellido().isBlank()) {
            userEntity.setApellido(dto.getApellido());
        }
        if (dto.getCorreo() != null && !dto.getCorreo().isBlank()) {
            userEntity.setCorreo(dto.getCorreo());
        }
        if (dto.getRol() != null && !dto.getRol().isBlank()) {
            userEntity.setRol(dto.getRol());
        }
        if (dto.getContraseña() != null && !dto.getContraseña().isBlank()) {
            userEntity.setContraseña(dto.getContraseña());
        }

    }

    public UsuarioLoginDTO userToDTO(Usuario usuario) {
        UsuarioLoginDTO usuarioLoginDTO = new UsuarioLoginDTO();
        usuarioLoginDTO.setUsername(usuario.getCorreo());
        usuarioLoginDTO.setPassword(usuario.getContraseña());
        return usuarioLoginDTO;
    }
}
