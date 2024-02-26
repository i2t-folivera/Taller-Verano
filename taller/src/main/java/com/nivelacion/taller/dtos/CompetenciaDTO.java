package com.nivelacion.taller.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CompetenciaDTO {

    private Long id;

    @NotNull(message = "Ingrese un nombre")
    @Size(min = 1)
    private String nombre;

    @NotNull(message = "Ingrese el estado")
    @Size(min = 1)
    private String estado;

    @NotNull(message = "Ingrese una fecha")
    private LocalDateTime fecha;

    private Boolean eliminado;

    @NotNull(message = "Ingrese un lugar")
    @Size(min = 1)
    private String lugar;

    private UsuarioDTO usuario;

    public CompetenciaDTO() {
    }

    public CompetenciaDTO(@NotNull(message = "Ingrese un nombre de competencia") @Size(min = 1) String nombre,
            @NotNull(message = "Ingrese un estado") @Size(min = 1) String estado,
            @NotNull(message = "Ingrese una fecha") LocalDateTime fecha,
            @NotNull(message = "Ingrese si la competencia ha sido eliminada") Boolean eliminado,
            @NotNull(message = "Ingrese un lugar") @Size(min = 1) String lugar,
            UsuarioDTO usuario) {
        this.nombre = nombre;
        this.estado = estado;
        this.fecha = fecha;
        this.eliminado = eliminado;
        this.lugar = lugar;
        this.usuario = usuario;
    }

    public CompetenciaDTO(Long id, @NotNull(message = "Ingrese un nombre de competencia") @Size(min = 1) String nombre,
            @NotNull(message = "Ingrese un estado") @Size(min = 1) String estado,
            @NotNull(message = "Ingrese una fecha") LocalDateTime fecha,
            @NotNull(message = "Ingrese si la competencia ha sido eliminada") Boolean eliminado,
            @NotNull(message = "Ingrese un lugar") @Size(min = 1) String lugar,
            UsuarioDTO usuario) {
        this.nombre = nombre;
        this.estado = estado;
        this.fecha = fecha;
        this.eliminado = eliminado;
        this.lugar = lugar;
        this.usuario = usuario;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
}
