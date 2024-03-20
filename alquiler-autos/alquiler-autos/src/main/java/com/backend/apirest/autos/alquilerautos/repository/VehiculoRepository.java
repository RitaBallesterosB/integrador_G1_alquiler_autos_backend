package com.backend.apirest.autos.alquilerautos.repository;

import com.backend.apirest.autos.alquilerautos.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehiculoRepository  extends JpaRepository <Vehiculo, Long> {
    Optional<Vehiculo> findById(Long id);
}
