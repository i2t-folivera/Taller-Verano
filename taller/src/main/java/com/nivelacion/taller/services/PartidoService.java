package com.nivelacion.taller.services;

import java.util.List;

import com.nivelacion.taller.dtos.PartidoDTO;
import com.nivelacion.taller.exceptions.EmptyListException;

public interface PartidoService {

    List<PartidoDTO> getPartidos() throws EmptyListException;

}
