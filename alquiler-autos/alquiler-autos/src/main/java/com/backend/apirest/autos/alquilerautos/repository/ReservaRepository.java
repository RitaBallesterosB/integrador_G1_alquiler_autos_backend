package com.backend.apirest.autos.alquilerautos.repository;

import com.backend.apirest.autos.alquilerautos.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    // Método para buscar reservas por id de vehiculo
  /* List<Reserva> findByIdVehiculo(Long idVehiculo);

    // Método para buscar reservas por id de usuario
    List<Reserva> findByIdUsuario(Long idUsuario);
*/
    // Método para buscar reserva por id de reserva
    Optional<Reserva> findById(Long id);

    List<Reserva> findByUsuarioIdUsuario(Long usuarioId);

}
