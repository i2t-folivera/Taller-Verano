package com.nivelacion.taller.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nivelacion.taller.dtos.PartidoDTO;
import com.nivelacion.taller.enums.Estado;
import com.nivelacion.taller.models.Partido;

@Component
public class PartidoMapper {

    public PartidoDTO original2DTO(Partido partido) {
        PartidoDTO dtoPartido = new PartidoDTO();
        dtoPartido.setId(partido.getId());
        dtoPartido.setGoles_local(partido.getGoles_local());
        dtoPartido.setGoles_visitante(partido.getGoles_visitante());
        dtoPartido.setFecha_realizacion(partido.getFecha_realizacion());
        dtoPartido.setFecha_baja(partido.getFecha_baja());
        dtoPartido.setCompetencia(new CompetenciaMapper().original2DTO(partido.getCompetencia()));
        // Mapear local y visitante
        dtoPartido.setLocal(new ParticipanteMapper().original2DTO(partido.getLocal()));
        dtoPartido.setVisitante(new ParticipanteMapper().original2DTO(partido.getVisitante()));
        return dtoPartido;
    }

    public Partido dto2Model(PartidoDTO partidoDTO) {
        Partido newPartido = new Partido();
        newPartido.setId(partidoDTO.getId());
        newPartido.setGoles_local(partidoDTO.getGoles_local());
        newPartido.setGoles_visitante(partidoDTO.getGoles_visitante());
        newPartido.setFecha_realizacion(partidoDTO.getFecha_realizacion());
        newPartido.setFecha_baja(partidoDTO.getFecha_baja());
        newPartido.setCompetencia(new CompetenciaMapper().dto2Model(partidoDTO.getCompetencia()));
        // Mapear local y visitante
        newPartido.setLocal(new ParticipanteMapper().dto2Model(partidoDTO.getLocal()));
        newPartido.setVisitante(new ParticipanteMapper().dto2Model(partidoDTO.getVisitante()));
        return newPartido;
    }

    public List<PartidoDTO> modelToDTO(List<Partido> partidosList) {
        return partidosList.stream()
                .map(this::original2DTO)
                .collect(Collectors.toList());
    }
}
