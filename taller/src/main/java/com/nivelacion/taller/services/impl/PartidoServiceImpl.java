package com.nivelacion.taller.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nivelacion.taller.dtos.PartidoDTO;
import com.nivelacion.taller.exceptions.EmptyListException;
import com.nivelacion.taller.exceptions.ModelNotFoundException;
import com.nivelacion.taller.mappers.PartidoMapper;
import com.nivelacion.taller.models.Competencia;
import com.nivelacion.taller.models.Partido;
import com.nivelacion.taller.repository.CompetenciaRepository;
import com.nivelacion.taller.repository.PartidoRepository;
import com.nivelacion.taller.services.PartidoService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PartidoServiceImpl implements PartidoService {

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private PartidoMapper partidoMapper;

    @Autowired
    private CompetenciaRepository competenciaRepository;

    @Override
    public List<PartidoDTO> getPartidos() throws EmptyListException {
        List<Partido> modelList = partidoRepository.findAll();
        if (modelList == null || modelList.isEmpty()) {
            throw new EmptyListException("Lista de partidos vacía");
        }

        return partidoMapper.modelToDTO(modelList);
    }

    @Override
    public PartidoDTO savePartido(PartidoDTO dto) throws ModelNotFoundException {
        // Verificar si la competencia asociada al partido existe
        Long competenciaId = dto.getCompetencia().getId();
        Competencia competencia = competenciaRepository.findById(competenciaId)
                .orElseThrow(() -> new ModelNotFoundException(competenciaId, "Competencia"));

        // Si tiene un ID, buscar el partido en la base de datos
        Partido model = null;
        if (dto.getId() != null && dto.getId() != 0) {
            model = partidoRepository.findById(dto.getId()).orElse(null);
            if (model == null) {
                throw new ModelNotFoundException(dto.getId(), "Partido");
            }
        }

        // Mapear DTO a modelo
        model = partidoMapper.dto2Model(dto);

        // Asignar la competencia al modelo de partido
        model.setCompetencia(competencia);

        // Guardar el partido en la base de datos
        Partido modelSaved = partidoRepository.save(model);

        // Mapear modelo a DTO
        PartidoDTO result = partidoMapper.original2DTO(modelSaved);

        return result;
    }
}
