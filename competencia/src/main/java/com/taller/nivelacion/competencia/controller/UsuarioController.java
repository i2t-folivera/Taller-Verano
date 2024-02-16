package com.taller.nivelacion.competencia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taller.nivelacion.competencia.dtos.UsuarioBasicDTO;
import com.taller.nivelacion.competencia.dtos.UsuarioDTO;
import com.taller.nivelacion.competencia.dtos.UsuarioLoginDTO;
import com.taller.nivelacion.competencia.exceptions.InvalidUserException;
import com.taller.nivelacion.competencia.models.Usuario;
import com.taller.nivelacion.competencia.services.UsuarioService;

@RestController
@RequestMapping(value = "/api/v1")
@CrossOrigin(origins = { "http://localhost:8080" }, methods = { RequestMethod.GET,
        RequestMethod.POST }, allowCredentials = "true")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    // @GetMapping
    // public List<UsuarioDTO> getUsersList() {
    // List<UsuarioDTO> dtoList = usuarioService.getUsersList();
    // return dtoList;
    // }
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getUsuarios() {
        return ResponseEntity.ok().body(usuarioService.getUsuarios());
    }

    @PostMapping(value = "/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
        UsuarioDTO dto = usuarioService.registerUserLoginDTO(usuarioDTO);// trae nombre, apellido, correo y contraseña
        UsuarioBasicDTO dtoResponse = new UsuarioBasicDTO();
        dtoResponse.setNombre(dto.getNombre());
        dtoResponse.setApellido(dto.getApellido());
        dtoResponse.setCorreo(dto.getCorreo());
        return new ResponseEntity<UsuarioBasicDTO>(dtoResponse, HttpStatus.OK);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<UsuarioLoginDTO> singIn(@Validated @RequestBody UsuarioLoginDTO usuarioLoginDTO)
            throws InvalidUserException {
        UsuarioLoginDTO userDTO = usuarioService.authUser(usuarioLoginDTO.getUsername(), usuarioLoginDTO.getPassword());
        return ResponseEntity.ok().body(userDTO);
    }
}
