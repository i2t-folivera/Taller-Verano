package com.taller.nivelacion.competencia.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioLoginDTO {

    @NotBlank(message = "Username es obligatorio")
    @Email(message = "Username debe ser un correo valido")
    private String username;
    @NotBlank(message = "Password es obligatorio")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String correo) {
        this.username = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String contraseña) {
        this.password = contraseña;
    }
}
