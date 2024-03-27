package com.nivelacion.taller.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nivelacion.taller.dtos.CompetenciaDTO;
import com.nivelacion.taller.dtos.ParticipanteDTO;
import com.nivelacion.taller.dtos.PartidoDTO;
import com.nivelacion.taller.enums.Estado;
import com.nivelacion.taller.exceptions.EmptyListException;
import com.nivelacion.taller.exceptions.ModelNotFoundException;
import com.nivelacion.taller.mappers.CompetenciaMapper;
import com.nivelacion.taller.models.Competencia;
import com.nivelacion.taller.models.Participante;
import com.nivelacion.taller.models.Partido;
import com.nivelacion.taller.models.Usuario;
import com.nivelacion.taller.repository.CompetenciaRepository;
import com.nivelacion.taller.repository.UsuarioRepository;
import com.nivelacion.taller.services.CompetenciaService;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Service
@Slf4j
public class CompetenciaServiceImpl implements CompetenciaService {

    @Autowired
    private CompetenciaRepository competenciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CompetenciaMapper competenciaMapper;

    @Autowired
    private ParticipanteServiceImpl participanteServiceImpl;

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

    public void generarFixture() {
        try {
            List<List<ParticipanteDTO>> enfrentamientos = generarEnfrentamientos(
                    participanteServiceImpl.getParticipantes());

            // Imprimir el fixture si es necesario
            for (int i = 0; i < enfrentamientos.size(); i++) {
                List<ParticipanteDTO> enfrentamiento = enfrentamientos.get(i);
                System.out.println("Partido " + (i + 1) + ": " + enfrentamiento.get(0).getNombre() + " vs "
                        + enfrentamiento.get(1).getNombre());
            }
        } catch (EmptyListException e) {
            System.out.println("Error al obtener la lista de participantes: " + e.getMessage());
        }
    }

    private List<List<ParticipanteDTO>> generarEnfrentamientos(List<ParticipanteDTO> participantes) {
        List<List<ParticipanteDTO>> enfrentamientos = new ArrayList<>();

        // Barajar los participantes para aleatorizar los enfrentamientos
        Collections.shuffle(participantes);

        // Generar los enfrentamientos en base a la cantidad de participantes
        for (int i = 0; i < participantes.size(); i += 2) {
            List<ParticipanteDTO> enfrentamiento = new ArrayList<>();
            enfrentamiento.add(participantes.get(i));
            enfrentamiento.add(participantes.get(i + 1));
            enfrentamientos.add(enfrentamiento);
        }

        return enfrentamientos;
    }

}
