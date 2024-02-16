package com.taller.nivelacion.competencia.services;

import java.util.List;

import com.taller.nivelacion.competencia.dtos.CompetenciaDTO;
import com.taller.nivelacion.competencia.exceptions.EmptyListException;
import com.taller.nivelacion.competencia.exceptions.ModelNotFoundException;

public interface CompetenciaService {
    CompetenciaDTO save(CompetenciaDTO dto) throws ModelNotFoundException;

    List<CompetenciaDTO> getCompetencias() throws EmptyListException;
}
