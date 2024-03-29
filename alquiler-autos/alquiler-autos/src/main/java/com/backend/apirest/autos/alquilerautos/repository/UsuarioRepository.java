package com.backend.apirest.autos.alquilerautos.repository;

import com.backend.apirest.autos.alquilerautos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreoElectronico(String correoElectronico);
    Optional<Usuario> findById(Long id);
}
