package com.nivelacion.taller.dtos;

import java.util.Collection;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.nivelacion.taller.enums.Role;

public class UsuarioDTO {

    private Long id;

    @NotNull(message = "Ingrese un nombre")
    @Size(min = 1)
    private String nombre;

    @NotNull(message = "Ingrese un apellido")
    @Size(min = 1)
    private String apellido;

    @NotNull(message = "Ingrese un correo")
    @Size(min = 1)
    private String correo;

    @NotNull(message = "Ingrese un rol")
    @Size(min = 1)
    private Collection<Role> roles;

    @NotNull(message = "Ingrese una contraseña")
    @Size(min = 1)
    private String contraseña;

    public UsuarioDTO() {
    }

    public UsuarioDTO(@NotNull(message = "Ingrese un nombre") @Size(min = 1) String nombre,
            @NotNull(message = "Ingrese un apellido") @Size(min = 1) String apellido,
            @NotNull(message = "Ingrese un correo") @Size(min = 1) String correo,
            @NotNull(message = "Ingrese un rol") @Size(min = 1) Collection<Role> roles,
            @NotNull(message = "Ingrese una contraseña") @Size(min = 1) String contraseña) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.roles = roles;
        this.contraseña = contraseña;
    }

    public UsuarioDTO(Long id, @NotNull(message = "Ingrese un nombre") @Size(min = 1) String nombre,
            @NotNull(message = "Ingrese un apellido") @Size(min = 1) String apellido,
            @NotNull(message = "Ingrese un correo") @Size(min = 1) String correo,
            @NotNull(message = "Ingrese un rol") @Size(min = 1) Collection<Role> roles,
            @NotNull(message = "Ingrese una contraseña") @Size(min = 1) String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.roles = roles;
        this.contraseña = contraseña;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
