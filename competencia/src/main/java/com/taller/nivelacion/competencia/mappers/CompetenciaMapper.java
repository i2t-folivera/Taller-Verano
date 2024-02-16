package com.taller.nivelacion.competencia.mappers;

import org.springframework.stereotype.Component;

import com.taller.nivelacion.competencia.dtos.CompetenciaDTO;
import com.taller.nivelacion.competencia.models.Competencia;

@Component
public class CompetenciaMapper {

    public Competencia competenciaDTO2Model(CompetenciaDTO dto) {
        Competencia competenciaModel = new Competencia();
        competenciaModel.setNombre(dto.getNombre());
        competenciaModel.setEstado(dto.getEstado());
        competenciaModel.setFecha(dto.getFecha());
        competenciaModel.setEliminado(dto.getEliminado());
        competenciaModel.setLugar(dto.getLugar());
        return competenciaModel;
    }

    public CompetenciaDTO competenciaModel2DTO(Competencia competenciaModel) {
        CompetenciaDTO dto = new CompetenciaDTO();
        dto.setId(competenciaModel.getId());
        dto.setNombre(competenciaModel.getNombre());
        dto.setEstado(competenciaModel.getEstado());
        dto.setFecha(competenciaModel.getFecha());
        dto.setEliminado(competenciaModel.getEliminado());
        dto.setLugar(competenciaModel.getLugar());
        return dto;
    }
}
