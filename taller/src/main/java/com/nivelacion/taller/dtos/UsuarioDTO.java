package com.nivelacion.taller.dtos;

import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.nivelacion.taller.enums.Role;
import com.nivelacion.taller.models.Competencia;

public class UsuarioDTO {

    private Long id;

    @NotNull(message = "Ingrese un nombre")
    @Size(min = 1)
    private String nombre;

    @NotNull(message = "Ingrese un apellido")
    @Size(min = 1)
    private String apellido;

    @NotNull(message = "Ingrese un mail")
    @Size(min = 1)
    private String mail;

    @NotNull(message = "Ingrese un rol")
    @Size(min = 1)
    private Collection<Role> roles;

    @NotNull(message = "Ingrese una contrasenia")
    @Size(min = 1)
    private String contrasenia;

    private List<Competencia> competencias;

    public UsuarioDTO() {
    }

    public UsuarioDTO(@NotNull(message = "Ingrese un nombre") @Size(min = 1) String nombre,
            @NotNull(message = "Ingrese un apellido") @Size(min = 1) String apellido,
            @NotNull(message = "Ingrese un mail") @Size(min = 1) String mail,
            @NotNull(message = "Ingrese un rol") @Size(min = 1) Collection<Role> roles,
            @NotNull(message = "Ingrese una contrasenia") @Size(min = 1) String contrasenia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.roles = roles;
        this.contrasenia = contrasenia;
    }

    public UsuarioDTO(Long id, @NotNull(message = "Ingrese un nombre") @Size(min = 1) String nombre,
            @NotNull(message = "Ingrese un apellido") @Size(min = 1) String apellido,
            @NotNull(message = "Ingrese un mail") @Size(min = 1) String mail,
            @NotNull(message = "Ingrese un rol") @Size(min = 1) Collection<Role> roles,
            @NotNull(message = "Ingrese una contrasenia") @Size(min = 1) String contrasenia) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.roles = roles;
        this.contrasenia = contrasenia;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public List<Competencia> getCompetencias() {
        return competencias;
    }

    public void setCompetencias(List<Competencia> competencias) {
        this.competencias = competencias;
    }
}
