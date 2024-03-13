package com.backend.apirest.autos.alquilerautos.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuarios")
    private Long id;

    private String nombre;
    private String apellido;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    private String contrasenia;
    private boolean administrador;

    // Relación uno a muchos con Reserva
    @OneToMany(mappedBy = "usuario")
    private List<Reserva> reservas;

    // Constructor, getters y setters

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String correoElectronico, String contrasenia, boolean administrador, List<Reserva> reservas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
        this.administrador = administrador;
        this.reservas = reservas;
    }
}
