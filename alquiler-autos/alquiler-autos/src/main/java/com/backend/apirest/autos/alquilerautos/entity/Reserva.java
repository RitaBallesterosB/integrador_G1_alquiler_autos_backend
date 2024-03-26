package com.backend.apirest.autos.alquilerautos.entity;


import javax.persistence.*;
import java.util.Date;

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

    @Column(name="fecha_entrega")
    private Date fechaEntrega;

    @Column(name="fecha_devolucion")
    private Date fechaDevolucion;

    // Constructor, getters y setters

    public Reserva() {
    }

    public Reserva(Long id, Vehiculo vehiculo, Usuario usuario, Date fechaEntrega, Date fechaDevolucion) {
        this.id = id;
        this.vehiculo = vehiculo;
        this.usuario = usuario;
        this.fechaEntrega = fechaEntrega;
        this.fechaDevolucion = fechaDevolucion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    /*@Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", vehiculo=" + vehiculo +
                ", usuario=" + usuario +
                ", fechaEntrega=" + fechaEntrega +
                ", fechaDevolucion=" + fechaDevolucion +
                '}';
    }*/
}