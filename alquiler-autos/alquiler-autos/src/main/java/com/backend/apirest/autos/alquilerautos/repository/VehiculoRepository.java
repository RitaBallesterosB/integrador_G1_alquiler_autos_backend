package com.backend.apirest.autos.alquilerautos.repository;

import com.backend.apirest.autos.alquilerautos.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoRepository  extends JpaRepository <Vehiculo, Long> {
}
