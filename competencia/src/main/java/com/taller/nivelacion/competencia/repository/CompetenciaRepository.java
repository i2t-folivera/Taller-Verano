package com.taller.nivelacion.competencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.taller.nivelacion.competencia.models.Competencia;

@Repository
public interface CompetenciaRepository extends JpaRepository<Competencia, Long> {

}
