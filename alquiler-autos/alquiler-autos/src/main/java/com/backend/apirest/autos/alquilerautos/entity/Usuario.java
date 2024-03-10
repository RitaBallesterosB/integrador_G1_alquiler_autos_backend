package com.backend.apirest.autos.alquilerautos.entity;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuarios")
    private Long idUsuario;

    private String nombre;
    private String apellido;

    @Column(name = "correo_electronico")
    private String correoElectronico;
    private String contrasenia;
    private Integer administrador;

    @OneToOne(mappedBy = "usuario")
    private Reserva reserva;

    public Usuario() {
    }

    public Usuario(Long idUsuario, String nombre, String apellido, String correoElectronico, String contrasenia, Integer administrador, Reserva reserva) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
        this.administrador = administrador;
        this.reserva = reserva;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public Integer isAdministrador() {
        return administrador;
    }

    public Reserva getReserva() {
        return reserva;
    }

}
