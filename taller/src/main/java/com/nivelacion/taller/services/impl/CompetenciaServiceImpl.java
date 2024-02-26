package com.nivelacion.taller.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nivelacion.taller.dtos.CompetenciaDTO;
import com.nivelacion.taller.exceptions.EmptyListException;
import com.nivelacion.taller.exceptions.ModelNotFoundException;
import com.nivelacion.taller.mappers.CompetenciaMapper;
import com.nivelacion.taller.models.Competencia;
import com.nivelacion.taller.models.Usuario;
import com.nivelacion.taller.repository.CompetenciaRepository;
import com.nivelacion.taller.repository.UsuarioRepository;
import com.nivelacion.taller.services.CompetenciaService;

import java.util.Optional;

@Service
public class CompetenciaServiceImpl implements CompetenciaService {

    @Autowired
    private CompetenciaRepository competenciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CompetenciaMapper competenciaMapper;

    @Override
    public List<CompetenciaDTO> getCompetencias() throws EmptyListException {
        List<Competencia> modelList = competenciaRepository.findAll();
        if (modelList == null || modelList.isEmpty()) {
            throw new EmptyListException("Lista de competencias vacía");
        }
        return competenciaMapper.modelToDTO(modelList);
    }

    @Override
    public CompetenciaDTO save(CompetenciaDTO dto) throws ModelNotFoundException {
        // Verificar si el usuario asociado a la competencia existe
        Long usuarioId = dto.getUsuario().getId();
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ModelNotFoundException(usuarioId, "Usuario"));

        // Si tiene un ID, buscar la competencia en la base de datos
        Competencia model = null;
        if (dto.getId() != null && dto.getId() != 0) {
            model = competenciaRepository.findById(dto.getId()).orElse(null);
            if (model == null) {
                throw new ModelNotFoundException(dto.getId(), "Competencia");
            }
        }

        // Mapear DTO a modelo
        model = competenciaMapper.dto2Model(dto);
        // Asignar el usuario al modelo de competencia
        model.setUsuario(usuario);

        // Guardar la competencia en la base de datos
        Competencia modelSaved = competenciaRepository.save(model);
        // Mapear modelo a DTO
        CompetenciaDTO result = competenciaMapper.original2DTO(modelSaved);

        return result;
    }

}
