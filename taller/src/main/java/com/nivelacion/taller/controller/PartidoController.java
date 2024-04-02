package com.nivelacion.taller.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nivelacion.taller.dtos.PartidoDTO;
import com.nivelacion.taller.exceptions.EmptyListException;
import com.nivelacion.taller.exceptions.ModelNotFoundException;
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

    @PostMapping("/partido/save")
    public ResponseEntity<Object> savePartido(@Valid @RequestBody PartidoDTO dto) {
        PartidoDTO dtoReturned = null;
        try {
            dtoReturned = this.partidoServiceImpl.savePartido(dto);
            dtoReturned.setFecha_realizacion(LocalDateTime.now());
        } catch (ModelNotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoReturned);
    }

}
