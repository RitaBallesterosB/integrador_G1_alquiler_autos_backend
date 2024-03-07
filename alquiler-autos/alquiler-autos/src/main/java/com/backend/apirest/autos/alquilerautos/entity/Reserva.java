package com.backend.apirest.autos.alquilerautos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservas")
    private Long idReserva;

    @OneToOne
    @JoinColumn(name = "VEHICULOS_id_vehiculos")
    @JsonIgnore
    private Vehiculo vehiculo;

    @OneToOne // ya que definimos en la BD que un vehiculo solo tiene una reserva
    @JoinColumn(name = "USUARIOS_id_usuarios")
    private Usuario usuario;

    // Constructor, getters y setters
}