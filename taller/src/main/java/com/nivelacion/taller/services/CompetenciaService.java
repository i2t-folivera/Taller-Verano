package com.nivelacion.taller.services;

import java.util.List;

import com.nivelacion.taller.dtos.CompetenciaDTO;
import com.nivelacion.taller.exceptions.EmptyListException;
import com.nivelacion.taller.exceptions.ModelNotFoundException;

public interface CompetenciaService {

    List<CompetenciaDTO> getCompetencias() throws EmptyListException;

    CompetenciaDTO save(CompetenciaDTO dto) throws ModelNotFoundException;
}
