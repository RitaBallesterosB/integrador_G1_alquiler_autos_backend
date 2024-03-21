package com.backend.apirest.autos.alquilerautos.repository;

import com.backend.apirest.autos.alquilerautos.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByTitulo(String titulo);
}