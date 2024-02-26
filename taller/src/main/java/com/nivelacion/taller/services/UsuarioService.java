package com.nivelacion.taller.services;

import com.nivelacion.taller.models.Usuario;

import java.util.List;

import com.nivelacion.taller.dtos.UsuarioDTO;
import com.nivelacion.taller.enums.Role;

public interface UsuarioService {
    Usuario saveUsuario(Usuario usuario);

    Role saveRole(Role role);

    void addRoleToUsuario(String correo, Role role);

    Usuario getUsuario(String correo);

    List<Usuario> getUsuarios();

    UsuarioDTO registerUserLoginDTO(UsuarioDTO usuarioDTO) throws Exception;

    Usuario updateUsuario(Usuario usuario, UsuarioDTO usuarioDTO);
}
