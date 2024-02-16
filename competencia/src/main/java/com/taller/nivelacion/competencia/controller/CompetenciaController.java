package com.taller.nivelacion.competencia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taller.nivelacion.competencia.dtos.CompetenciaDTO;
import com.taller.nivelacion.competencia.exceptions.EmptyListException;
import com.taller.nivelacion.competencia.exceptions.ModelNotFoundException;
import com.taller.nivelacion.competencia.services.impl.CompetenciaServiceImpl;

@RestController
@RequestMapping("/api/v1/competencia")
@CrossOrigin(origins = { "http://localhost:8080" }, methods = { RequestMethod.GET,
        RequestMethod.POST }, allowCredentials = "true")
public class CompetenciaController {

    @Autowired
    private CompetenciaServiceImpl competenciaServiceImpl;

    @GetMapping
    public List<CompetenciaDTO> getCompetencias() throws EmptyListException {
        return competenciaServiceImpl.getCompetencias();
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody CompetenciaDTO dto) {
        CompetenciaDTO dtoReturned = null;

        try {
            dtoReturned = this.competenciaServiceImpl.save(dto);
        } catch (ModelNotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoReturned);
    }
}
