package com.nivelacion.taller.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nivelacion.taller.dtos.UsuarioDTO;
import com.nivelacion.taller.enums.Role;
import com.nivelacion.taller.exceptions.ModelNotFoundException;
import com.nivelacion.taller.mappers.UsuarioMapper;
import com.nivelacion.taller.models.Usuario;
import com.nivelacion.taller.repository.UsuarioRepository;
import com.nivelacion.taller.services.UsuarioService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
@CrossOrigin
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    // @GetMapping("/usuarios")
    // public ResponseEntity<List<Usuario>> getUsuarios() {
    // return ResponseEntity.ok().body(usuarioService.getUsuarios());
    // }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioDTO>> getUsuarios() {
        List<Usuario> usuarios = usuarioService.getUsuarios();
        List<UsuarioDTO> usuariosDTO = usuarios.stream()
                .map(usuario -> {
                    UsuarioDTO dto = usuarioMapper.originalToDTO(usuario); // Utilizamos el mapper para mapear el
                                                                           // Usuario a UsuarioDTO
                    dto.setCompetencias(usuario.getCompetencias()); // Asignamos las competencias
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(usuariosDTO);
    }

    // @PostMapping(value = "/usuario/save")
    @PostMapping(value = "/usuario/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@RequestBody UsuarioDTO usuarioDTO)
            throws Exception {
        UsuarioDTO dto = usuarioService.registerUserLoginDTO(usuarioDTO);// trae nombre, apellido, mail y contrasenia
        UsuarioDTO dtoResponse = new UsuarioDTO();
        dtoResponse.setNombre(dto.getNombre());
        dtoResponse.setApellido(dto.getApellido());
        dtoResponse.setMail(dto.getMail());
        dtoResponse.setContrasenia(dto.getContrasenia());
        dtoResponse.setRoles(dto.getRoles());
        return new ResponseEntity<UsuarioDTO>(dtoResponse, HttpStatus.OK);
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/role/save").toUriString());
        return ResponseEntity.created(uri).body(usuarioService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        // usuarioService.addRoleToUsuario(form.getUsername(), form.getRolename());
        // return ResponseEntity.ok().build();
        Usuario usuario = usuarioRepository.findByMail(form.getUsername());
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        Role role = Role.valueOf(form.getRolename()); // Convertir el nombre del rol a enum

        usuario.getRoles().add(role);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                Usuario usuario = usuarioService.getUsuario(username);
                String access_token = JWT.create()
                        .withSubject(usuario.getMail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",
                                usuario.getRoles().stream().map(Role::getName)
                                        .collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(HttpStatus.FORBIDDEN.value());
                // response.sendError(HttpStatus.FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

    @PutMapping("usuario/update/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
        try {
            // Obtener el usuario de la base de datos
            Usuario usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new ModelNotFoundException(id, "Usuario"));

            // Actualizar el usuario con la información del DTO
            Usuario usuarioActualizado = usuarioService.updateUsuario(usuario, dto);

            // Guardar el usuario actualizado en la base de datos
            usuarioRepository.save(usuarioActualizado);

            return ResponseEntity.ok(usuarioActualizado);
        } catch (ModelNotFoundException e) {
            // Manejar la excepción de modelo no encontrada
            return ResponseEntity.notFound().build(); // O puedes devolver otro tipo de respuesta según tus necesidades
        }
    }
}

@Data
class RoleToUserForm {
    private String username;
    private String rolename;
}