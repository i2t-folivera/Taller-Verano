package com.nivelacion.taller.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "partido")
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "goles_local")
    private Integer goles_local;

    @Column(name = "goles_visitante")
    private Integer goles_visitante;

    @Column(name = "fecha_realizacion")
    @DateTimeFormat(pattern = "dd-MM-YYYY HH:mm:ss")
    private LocalDateTime fecha_realizacion;

    @Column(name = "fecha_baja")
    @DateTimeFormat(pattern = "dd-MM-YYYY HH:mm:ss")
    private LocalDateTime fecha_baja;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_competencia")
    @JsonBackReference
    private Competencia competencia; // Relación con la competencia

    // Relacion mucho a mucho con Partido a traves de tabla intermedia
    // Participantes_partidos
    @ManyToMany
    @JoinTable(name = "participantes_partidos", joinColumns = @JoinColumn(name = "partido_id"), inverseJoinColumns = @JoinColumn(name = "participante_id"))
    private List<Participante> participantes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_local")
    private Participante local;

    @ManyToOne
    @JoinColumn(name = "id_visitante")
    private Participante visitante;

}
