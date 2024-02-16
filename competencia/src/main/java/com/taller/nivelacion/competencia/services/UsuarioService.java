package com.taller.nivelacion.competencia.services;

import java.util.List;

import com.taller.nivelacion.competencia.dtos.UsuarioDTO;
import com.taller.nivelacion.competencia.dtos.UsuarioLoginDTO;
import com.taller.nivelacion.competencia.exceptions.InvalidUserException;
import com.taller.nivelacion.competencia.models.Usuario;
import com.taller.nivelacion.competencia.models.Role;

public interface UsuarioService {

    List<UsuarioDTO> getUsersList();

    Usuario findByCorreo(String correo);

    UsuarioDTO registerUserLoginDTO(UsuarioDTO usuarioDTO) throws Exception;

    UsuarioLoginDTO authUser(String correo, String password) throws InvalidUserException;

    Boolean existsByEmail(String correo);

    UsuarioLoginDTO userToDTO(Usuario usuario);

    // para rolear usuario
    Usuario saveUsuario(Usuario usuario);

    Role saveRole(Role role);

    void addRoleToUsuario(String username, String roleName);

    Usuario getUsuario(String username);

    List<Usuario> getUsuarios();
}
