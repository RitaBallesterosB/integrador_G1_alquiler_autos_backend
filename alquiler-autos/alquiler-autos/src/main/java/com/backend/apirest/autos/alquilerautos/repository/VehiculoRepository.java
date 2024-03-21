package com.backend.apirest.autos.alquilerautos.repository;

import com.backend.apirest.autos.alquilerautos.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface VehiculoRepository  extends JpaRepository <Vehiculo, Long> {

    // Método para buscar vehículos por nombre que contenga la consulta
    List<Vehiculo> findByNombreContainingIgnoreCase(String consulta);

    // Método para buscar vehículos por nombre que contenga la consulta y pertenezca a alguna de las categorías proporcionadas
    List<Vehiculo> findByNombreContainingIgnoreCaseAndCategoria_IdIn(String consulta, List<Long> categoriaIds);

    // Método para buscar vehículos por categorías proporcionadas
    List<Vehiculo> findByCategoria_IdIn(List<Long> categoriaIds);

    // Método para buscar vehículos reservados entre las fechas de entrega y devolución
    List<Vehiculo> findByReservas_FechaEntregaBetweenAndReservas_FechaDevolucionBetween(Date fechaInicioEntrega, Date fechaFinEntrega, Date fechaInicioDevolucion, Date fechaFinDevolucion);

    Optional<Vehiculo> findById(Long id);

}
