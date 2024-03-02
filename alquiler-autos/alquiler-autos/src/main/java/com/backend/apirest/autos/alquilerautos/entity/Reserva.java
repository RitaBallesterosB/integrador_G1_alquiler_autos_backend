package com.backend.apirest.autos.alquilerautos.entity;

import javax.persistence.*;

@Entity
@Table(name = "RESERVAS")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservas")
    private Long idReserva;

    @OneToOne
    @JoinColumn(name = "VEHICULOS_id_vehiculo")
    private Vehiculo vehiculo;

    @OneToOne // ya que definimos en la BD que un vehiculo solo tiene una reserva
    @JoinColumn(name = "USUARIOS_id_usuario")
    private Usuario usuario;

    // Constructor, getters y setters
}