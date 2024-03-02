package com.backend.apirest.autos.alquilerautos.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "VEHICULOS", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nombre"})
})
public class Vehiculo {
    @Column(name="id_vehiculo")
    private Long id;

    private String nombre;


    private String descripcion;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_imagen")
    private Imagen imagen;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "VEHICULOS_has_CARACTERISTICAS",
            joinColumns = @JoinColumn(name = "VEHICULO_id_vehiculo"),
            inverseJoinColumns = @JoinColumn(name = "CARACTERISTICAS_id_caracteristicas")
    )
    private Set<Caracteristica> caracteristicas;

    @OneToOne(mappedBy = "vehiculo")
    private Reserva reserva;

    public Vehiculo() {
    }

    public Vehiculo(Long id, String nombre, String descripcion, Imagen imagen, Categoria categoria, Set<Caracteristica> caracteristicas, Reserva reserva) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.categoria = categoria;
        this.caracteristicas = caracteristicas;
        this.reserva = reserva;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Set<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(Set<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}
