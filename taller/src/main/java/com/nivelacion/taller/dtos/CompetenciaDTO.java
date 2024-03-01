package com.nivelacion.taller.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.nivelacion.taller.enums.Estado;

public class CompetenciaDTO {

    private Long id;

    @NotNull(message = "Ingrese un nombre")
    @Size(min = 1)
    private String nombre;

    @NotNull(message = "Ingrese el estado")
    private Estado estado;

    @NotNull(message = "Ingrese una fecha de baja")
    private LocalDateTime fecha_baja;

    @NotNull(message = "Ingrese una fecha de inicio")
    private LocalDateTime fecha_inicio;

    @NotNull(message = "Ingrese una fecha de creación")
    private LocalDateTime fecha_creacion;

    private UsuarioDTO usuario;

    public CompetenciaDTO() {
    }

    public CompetenciaDTO(Long id, @NotNull(message = "Ingrese un nombre") @Size(min = 1) String nombre,
            @NotNull(message = "Ingrese el estado") @Size(min = 1) Estado estado,
            @NotNull(message = "Ingrese una fecha de baja") LocalDateTime fecha_baja,
            @NotNull(message = "Ingrese una fecha de inicio") LocalDateTime fecha_inicio,
            @NotNull(message = "Ingrese una fecha de creación") LocalDateTime fecha_creacion,
            UsuarioDTO usuario) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.fecha_baja = fecha_baja;
        this.fecha_inicio = fecha_inicio;
        this.fecha_creacion = fecha_creacion;
        this.usuario = usuario;
    }
    // Getters y setters

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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha_baja() {
        return fecha_baja;
    }

    public void setFecha_baja(LocalDateTime fecha_baja) {
        this.fecha_baja = fecha_baja;
    }

    public LocalDateTime getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDateTime fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

}
