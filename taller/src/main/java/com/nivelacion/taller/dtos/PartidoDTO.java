package com.nivelacion.taller.dtos;

import java.time.LocalDateTime;

public class PartidoDTO {

    private Long id;
    private Integer goles_local;
    private Integer goles_visitante;
    private LocalDateTime fecha_realizacion;
    private LocalDateTime fecha_baja;
    private CompetenciaDTO competencia;

    public PartidoDTO() {
    }

    public PartidoDTO(Long id, Integer goles_local, Integer goles_visitante, LocalDateTime fecha_realizacion,
            LocalDateTime fecha_baja, CompetenciaDTO competencia) {
        this.id = id;
        this.goles_local = goles_local;
        this.goles_visitante = goles_visitante;
        this.fecha_realizacion = fecha_realizacion;
        this.fecha_baja = fecha_baja;
        this.competencia = competencia;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGoles_local() {
        return goles_local;
    }

    public void setGoles_local(Integer goles_local) {
        this.goles_local = goles_local;
    }

    public Integer getGoles_visitante() {
        return goles_visitante;
    }

    public void setGoles_visitante(Integer goles_visitante) {
        this.goles_visitante = goles_visitante;
    }

    public LocalDateTime getFecha_realizacion() {
        return fecha_realizacion;
    }

    public void setFecha_realizacion(LocalDateTime fecha_realizacion) {
        this.fecha_realizacion = fecha_realizacion;
    }

    public LocalDateTime getFecha_baja() {
        return fecha_baja;
    }

    public void setFecha_baja(LocalDateTime fecha_baja) {
        this.fecha_baja = fecha_baja;
    }

    public CompetenciaDTO getCompetencia() {
        return competencia;
    }

    public void setCompetencia(CompetenciaDTO competencia) {
        this.competencia = competencia;
    }
}
