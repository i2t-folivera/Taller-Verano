package com.nivelacion.taller.services;

import java.util.List;

import com.nivelacion.taller.dtos.ParticipanteDTO;
import com.nivelacion.taller.exceptions.EmptyListException;

public interface ParticipanteService {

    List<ParticipanteDTO> getParticipantes() throws EmptyListException;

}
