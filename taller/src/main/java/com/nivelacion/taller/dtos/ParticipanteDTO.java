package com.nivelacion.taller.dtos;

import java.time.LocalDateTime;

public class ParticipanteDTO {

    private Long id;
    private String nombre;
    private String colores;
    private String trofeos;
    private LocalDateTime fecha_baja;

    public ParticipanteDTO() {
    }

    public ParticipanteDTO(Long id, String nombre, String colores, String trofeos, LocalDateTime fecha_baja) {
        this.id = id;
        this.nombre = nombre;
        this.colores = colores;
        this.trofeos = trofeos;
        this.fecha_baja = fecha_baja;
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

    public String getColores() {
        return colores;
    }

    public void setColores(String colores) {
        this.colores = colores;
    }

    public String getTrofeos() {
        return trofeos;
    }

    public void setTrofeos(String trofeos) {
        this.trofeos = trofeos;
    }

    public LocalDateTime getFecha_baja() {
        return fecha_baja;
    }

    public void setFecha_baja(LocalDateTime fecha_baja) {
        this.fecha_baja = fecha_baja;
    }
}
