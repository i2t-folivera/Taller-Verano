package com.nivelacion.taller.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nivelacion.taller.dtos.CompetenciaDTO;
import com.nivelacion.taller.enums.Estado;
import com.nivelacion.taller.models.Competencia;

@Component
public class CompetenciaMapper {

    public CompetenciaDTO original2DTO(Competencia competencia) {
        CompetenciaDTO dtoCompetencia = new CompetenciaDTO();
        dtoCompetencia.setId(competencia.getId()); // Agregar el ID de la competencia
        dtoCompetencia.setNombre(competencia.getNombre());
        dtoCompetencia.setEstado(Estado.fromValor(competencia.getEstado().ordinal()));
        dtoCompetencia.setFecha_baja(competencia.getFecha_baja());
        dtoCompetencia.setFecha_inicio(competencia.getFecha_inicio());
        dtoCompetencia.setFecha_creacion(competencia.getFecha_creacion());
        dtoCompetencia.setUsuario(new UsuarioMapper().originalToDTO(competencia.getUsuario()));
        return dtoCompetencia;
    }

    public Competencia dto2Model(CompetenciaDTO competenciaDTO) {
        Competencia newCompetencia = new Competencia();
        newCompetencia.setId(competenciaDTO.getId());
        newCompetencia.setNombre(competenciaDTO.getNombre());
        newCompetencia.setEstado(Estado.fromValor(competenciaDTO.getEstado().ordinal())); // Asignación directa del enum
                                                                                          // Estado
        newCompetencia.setFecha_baja(competenciaDTO.getFecha_baja());
        newCompetencia.setFecha_inicio(competenciaDTO.getFecha_inicio());
        newCompetencia.setFecha_creacion(competenciaDTO.getFecha_creacion());
        newCompetencia.setUsuario(new UsuarioMapper().dto2Model(competenciaDTO.getUsuario()));
        return newCompetencia;
    }

    public List<CompetenciaDTO> modelToDTO(List<Competencia> competenciasList) {
        return competenciasList.stream()
                .map(this::original2DTO)
                .collect(Collectors.toList());
    }

}
