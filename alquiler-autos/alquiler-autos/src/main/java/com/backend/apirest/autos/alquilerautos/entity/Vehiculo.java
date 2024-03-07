package com.backend.apirest.autos.alquilerautos.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vehiculos", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nombre"})
})
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_vehiculos")
    private Long id;

    private String nombre;

    private String descripcion;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagen> imagenes;

    @ManyToOne
    @JoinColumn(name = "CATEGORIAS_id_categorias")
    private Categoria categoria;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "VEHICULOS_has_CARACTERISTICAS",
            joinColumns = @JoinColumn(name = "VEHICULOS_id_vehiculos"),
            inverseJoinColumns = @JoinColumn(name = "CARACTERISTICAS_id_caracteristicas")
    )
    private List<Caracteristica> caracteristicas;

    @OneToOne(mappedBy = "vehiculo")
    private Reserva reserva;

    public Vehiculo() {
    }

    public Vehiculo(Long id, String nombre, String descripcion, List<Imagen> imagenes, Categoria categoria, List<Caracteristica> caracteristicas, Reserva reserva) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
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

    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}

