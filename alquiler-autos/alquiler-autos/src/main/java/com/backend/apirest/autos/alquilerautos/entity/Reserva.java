package com.backend.apirest.autos.alquilerautos.entity;


import javax.persistence.*;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservas")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "VEHICULOS_id_vehiculos")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "USUARIOS_id_usuarios")
    private Usuario usuario;

    // Constructor, getters y setters
}