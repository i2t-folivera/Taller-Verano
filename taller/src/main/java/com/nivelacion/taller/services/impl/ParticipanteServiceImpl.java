package com.nivelacion.taller.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nivelacion.taller.dtos.ParticipanteDTO;
import com.nivelacion.taller.exceptions.EmptyListException;
import com.nivelacion.taller.mappers.ParticipanteMapper;
import com.nivelacion.taller.models.Participante;
import com.nivelacion.taller.repository.ParticipanteRepository;
import com.nivelacion.taller.services.ParticipanteService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ParticipanteServiceImpl implements ParticipanteService {

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Autowired
    private ParticipanteMapper participanteMapper;

    @Override
    public List<ParticipanteDTO> getParticipantes() throws EmptyListException {
        List<Participante> modelList = participanteRepository.findAll();
        if (modelList == null || modelList.isEmpty()) {
            throw new EmptyListException("Lista de participantes vacía");
        }

        return participanteMapper.modelToDTO(modelList);
    }
}
