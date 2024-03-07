package com.backend.apirest.autos.alquilerautos.repository;


import com.backend.apirest.autos.alquilerautos.entity.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagenRepository extends JpaRepository<Imagen, Long> {
    List<Imagen> findByVehiculoId(Long vehiculoId);
}
