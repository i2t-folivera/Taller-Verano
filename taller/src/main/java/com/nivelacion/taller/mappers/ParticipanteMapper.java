package com.nivelacion.taller.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nivelacion.taller.dtos.ParticipanteDTO;
import com.nivelacion.taller.models.Participante;

@Component
public class ParticipanteMapper {

    public ParticipanteDTO original2DTO(Participante participante) {
        ParticipanteDTO dtoParticipante = new ParticipanteDTO();
        dtoParticipante.setId(participante.getId());
        dtoParticipante.setNombre(participante.getNombre());
        dtoParticipante.setColores(participante.getColores());
        dtoParticipante.setTrofeos(participante.getTrofeos());
        dtoParticipante.setFecha_baja(participante.getFecha_baja());
        return dtoParticipante;
    }

    public Participante dto2Model(ParticipanteDTO participanteDTO) {
        Participante newParticipante = new Participante();
        newParticipante.setId(participanteDTO.getId());
        newParticipante.setNombre(participanteDTO.getNombre());
        newParticipante.setColores(participanteDTO.getColores());
        newParticipante.setTrofeos(participanteDTO.getTrofeos());
        newParticipante.setFecha_baja(participanteDTO.getFecha_baja());
        return newParticipante;
    }

    public List<ParticipanteDTO> modelToDTO(List<Participante> participantesList) {
        return participantesList.stream()
                .map(this::original2DTO)
                .collect(Collectors.toList());
    }
}
