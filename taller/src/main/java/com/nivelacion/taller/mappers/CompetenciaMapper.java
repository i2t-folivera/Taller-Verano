package com.nivelacion.taller.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nivelacion.taller.dtos.CompetenciaDTO;
import com.nivelacion.taller.models.Competencia;

@Component
public class CompetenciaMapper {

    public CompetenciaDTO original2DTO(Competencia competencia) {
        CompetenciaDTO dtoCompetencia = new CompetenciaDTO();
        dtoCompetencia.setNombre(competencia.getNombre());
        dtoCompetencia.setEstado(competencia.getEstado());
        dtoCompetencia.setFecha(competencia.getFecha());
        dtoCompetencia.setEliminado(competencia.getEliminado());
        dtoCompetencia.setLugar(competencia.getLugar());
        dtoCompetencia.setUsuario(new UsuarioMapper().originalToDTO(competencia.getUsuario()));
        return dtoCompetencia;
    }

    public Competencia dto2Model(CompetenciaDTO competenciaDTO) {
        Competencia newCompetencia = new Competencia();
        newCompetencia.setNombre(competenciaDTO.getNombre());
        newCompetencia.setEstado(competenciaDTO.getEstado());
        newCompetencia.setFecha(competenciaDTO.getFecha());
        newCompetencia.setEliminado(competenciaDTO.getEliminado());
        newCompetencia.setLugar(competenciaDTO.getLugar());
        newCompetencia.setUsuario(new UsuarioMapper().dto2Model(competenciaDTO.getUsuario()));
        return newCompetencia;
    }

    public List<CompetenciaDTO> modelToDTO(List<Competencia> competenciasList) {
        return competenciasList.stream()
                .map(this::original2DTO)
                .collect(Collectors.toList());
    }

}
