package com.nivelacion.taller.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nivelacion.taller.dtos.CompetenciaDTO;
import com.nivelacion.taller.dtos.UsuarioDTO;
import com.nivelacion.taller.enums.Estado;
import com.nivelacion.taller.exceptions.ModelNotFoundException;
import com.nivelacion.taller.models.Competencia;
import com.nivelacion.taller.models.Usuario;

@Component
public class CompetenciaMapper {

    public CompetenciaDTO original2DTO(Competencia competencia) {
        CompetenciaDTO dtoCompetencia = new CompetenciaDTO();
        dtoCompetencia.setId(competencia.getId()); // Agregar el ID de la competencia
        dtoCompetencia.setNombre(competencia.getNombre());
        // dtoCompetencia.setEstado(Estado.fromValor(competencia.getEstado().ordinal()));
        // Verificar si el estado no es nulo antes de llamar a ordinal()
        Estado estado = competencia.getEstado();
        if (estado != null) {
            dtoCompetencia.setEstado(Estado.fromValor(estado.ordinal()));
        } else {
            try {
                throw new ModelNotFoundException(competencia.getId(), "Competencia");
            } catch (ModelNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        dtoCompetencia.setFecha_baja(competencia.getFecha_baja());
        dtoCompetencia.setFecha_inicio(competencia.getFecha_inicio());
        dtoCompetencia.setFecha_creacion(competencia.getFecha_creacion());
        // dtoCompetencia.setUsuario(new
        // UsuarioMapper().originalToDTO(competencia.getUsuario()));
        // Verificar si el objeto Usuario en Competencia no es nulo antes de convertirlo
        // a DTO
        Usuario usuario = competencia.getUsuario();
        if (usuario != null) {
            dtoCompetencia.setUsuario(new UsuarioMapper().originalToDTO(usuario));
        } else {
            try {
                throw new ModelNotFoundException(competencia.getId(), "Competencia");
            } catch (ModelNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return dtoCompetencia;
    }

    public Competencia dto2Model(CompetenciaDTO competenciaDTO) {
        Competencia newCompetencia = new Competencia();
        newCompetencia.setId(competenciaDTO.getId());
        newCompetencia.setNombre(competenciaDTO.getNombre());
        // newCompetencia.setEstado(Estado.fromValor(competenciaDTO.getEstado().ordinal()));
        // // Asignación directa del enum
        // Estado

        Estado estado = competenciaDTO.getEstado();
        if (estado != null) {
            newCompetencia.setEstado(Estado.fromValor(estado.ordinal()));
        } else {
            try {
                throw new ModelNotFoundException(competenciaDTO.getId(), "Competencia");
            } catch (ModelNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        newCompetencia.setFecha_baja(competenciaDTO.getFecha_baja());
        newCompetencia.setFecha_inicio(competenciaDTO.getFecha_inicio());
        newCompetencia.setFecha_creacion(competenciaDTO.getFecha_creacion());
        // newCompetencia.setUsuario(new
        // UsuarioMapper().dto2Model(competenciaDTO.getUsuario()));
        // Verificar si el objeto UsuarioDTO en CompetenciaDTO no es nulo antes de
        // convertirlo a modelo
        UsuarioDTO usuarioDTO = competenciaDTO.getUsuario();
        if (usuarioDTO != null) {
            newCompetencia.setUsuario(new UsuarioMapper().dto2Model(usuarioDTO));
        } else {
            try {
                throw new ModelNotFoundException(competenciaDTO.getId(), "Competencia");
            } catch (ModelNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return newCompetencia;
    }

    public List<CompetenciaDTO> modelToDTO(List<Competencia> competenciasList) {
        return competenciasList.stream()
                .map(this::original2DTO)
                .collect(Collectors.toList());
    }

}
