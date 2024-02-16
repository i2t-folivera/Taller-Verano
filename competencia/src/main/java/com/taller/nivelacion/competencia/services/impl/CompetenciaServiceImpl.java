package com.taller.nivelacion.competencia.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller.nivelacion.competencia.dtos.CompetenciaDTO;
import com.taller.nivelacion.competencia.exceptions.EmptyListException;
import com.taller.nivelacion.competencia.exceptions.ModelNotFoundException;
import com.taller.nivelacion.competencia.mappers.CompetenciaMapper;
import com.taller.nivelacion.competencia.models.Competencia;
import com.taller.nivelacion.competencia.repository.CompetenciaRepository;
import com.taller.nivelacion.competencia.services.CompetenciaService;

@Service
public class CompetenciaServiceImpl implements CompetenciaService {

    @Autowired
    private CompetenciaRepository competenciaRepository;

    @Autowired
    private CompetenciaMapper competenciaMapper;

    @Override
    public CompetenciaDTO save(CompetenciaDTO dto) throws ModelNotFoundException {
        Competencia model = null;
        // Si tiene un id lo busco en la base de datos sino lo encuentro lanzo una
        // excepcion.
        if (dto.getId() != null && dto.getId() != 0) {
            model = this.competenciaRepository.findById(dto.getId()).orElse(null);
            // Esta excepcion arreglarse si eventualmente se usa el metodo.
            if (model == null) {
                // Excepcion de tipo check heredar de la clase exception
                throw new ModelNotFoundException(dto.getId(), "Competencia");
            }
        }
        model = this.competenciaMapper.competenciaDTO2Model(dto);
        Competencia modelSaved = competenciaRepository.save(model);
        CompetenciaDTO result = competenciaMapper.competenciaModel2DTO(modelSaved);

        return result;
    }

    @Override
    public List<CompetenciaDTO> getCompetencias() throws EmptyListException {
        List<Competencia> modelList = null;

        modelList = this.competenciaRepository.findAll();
        if (modelList == null) {
            throw new EmptyListException("Lista vacía");
        }
        List<CompetenciaDTO> dtoList = new ArrayList<>();
        modelList.forEach(competencia -> {
            dtoList.add(competenciaMapper.competenciaModel2DTO(competencia));
        });
        return dtoList;
    }

}
