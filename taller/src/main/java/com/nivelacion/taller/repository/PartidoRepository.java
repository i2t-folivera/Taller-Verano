package com.nivelacion.taller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nivelacion.taller.models.Partido;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {

}
