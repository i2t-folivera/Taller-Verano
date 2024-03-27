package com.nivelacion.taller.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nivelacion.taller.dtos.ParticipanteDTO;
import com.nivelacion.taller.dtos.PartidoDTO;
import com.nivelacion.taller.exceptions.EmptyListException;
import com.nivelacion.taller.services.impl.ParticipanteServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
@CrossOrigin
public class ParticipanteController {

    @Autowired
    private ParticipanteServiceImpl participanteServiceImpl;

    @GetMapping("/participantes")
    public ResponseEntity<List<ParticipanteDTO>> getParticipantes() {
        try {
            List<ParticipanteDTO> participanteDTO = participanteServiceImpl.getParticipantes();
            return ResponseEntity.ok().body(participanteDTO);
        } catch (EmptyListException e) {
            return ResponseEntity.noContent().build(); // Devuelve código 204 si la lista está vacía
        }
    }

}
