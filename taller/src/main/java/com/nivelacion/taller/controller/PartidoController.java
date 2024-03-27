package com.nivelacion.taller.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nivelacion.taller.dtos.PartidoDTO;
import com.nivelacion.taller.exceptions.EmptyListException;
import com.nivelacion.taller.services.impl.PartidoServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
@CrossOrigin
public class PartidoController {

    @Autowired
    private PartidoServiceImpl partidoServiceImpl;

    @GetMapping("/partidos")
    public ResponseEntity<List<PartidoDTO>> getPartidos() {
        try {
            List<PartidoDTO> partidosDTO = partidoServiceImpl.getPartidos();
            return ResponseEntity.ok().body(partidosDTO);
        } catch (EmptyListException e) {
            return ResponseEntity.noContent().build(); // Devuelve código 204 si la lista está vacía
        }
    }

}
