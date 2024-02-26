package com.nivelacion.taller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nivelacion.taller.models.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByCorreo(String correo);

    Boolean existsByCorreo(String correo);

    Optional<Usuario> findById(Long id);
}
