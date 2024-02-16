package com.taller.nivelacion.competencia.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CompetenciaDTO {

    private Long id;

    @NotNull(message = "Ingrese un nombre")
    @Size(min = 1)
    private String nombre;

    @NotNull
    @Size(min = 1)
    private String estado;

    @NotNull(message = "Ingrese una fecha")
    private LocalDateTime fecha;

    private Boolean eliminado;

    @NotNull(message = "Ingrese un lugar")
    @Size(min = 1)
    private String lugar;

    public CompetenciaDTO() {
    }

    public CompetenciaDTO(String nombre, String estado, LocalDateTime fecha, Boolean eliminado, String lugar) {
        this.nombre = nombre;
        this.estado = estado;
        this.fecha = fecha;
        this.eliminado = eliminado;
        this.lugar = lugar;
    }

    public CompetenciaDTO(Long id, String nombre, String estado, LocalDateTime fecha, Boolean eliminado, String lugar) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.fecha = fecha;
        this.eliminado = eliminado;
        this.lugar = lugar;
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

}
