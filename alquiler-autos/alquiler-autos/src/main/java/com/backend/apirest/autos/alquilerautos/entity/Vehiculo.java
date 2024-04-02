package com.backend.apirest.autos.alquilerautos.entity;

import javax.persistence.*;

import java.util.ArrayList;

import java.util.List;


@Entity
@Table(name = "vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_vehiculos")
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    private String descripcion;

    // Relación uno a muchos con Imagen
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagen> imagenes;

    // Relación muchos a uno con Categoria (una categoria pertenece a muchos vehiculos)
    @ManyToOne
    @JoinColumn(name = "CATEGORIAS_id_categorias")
    private Categoria categoria;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "vehiculos_has_caracteristicas",
            joinColumns = @JoinColumn(name = "VEHICULOS_id_vehiculos"),
            inverseJoinColumns = @JoinColumn(name = "CARACTERISTICAS_id_caracteristicas")
    )
    private List<Caracteristica> caracteristicas;

    // Relación uno a muchos con Reserva (un vehículo puede tener muchas reservas)
    @OneToMany(mappedBy = "vehiculo")
    private List<Reserva> reservas;

    public Vehiculo() {
    }

    public Vehiculo(String nombre, String descripcion, List<Imagen> imagenes, Categoria categoria, List<Caracteristica> caracteristicas, List<Reserva> reservas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
        this.categoria = categoria;
        this.caracteristicas = caracteristicas;
        this.reservas = reservas;
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

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagenes=" + imagenes +
                ", categoria=" + categoria +
                ", caracteristicas=" + caracteristicas+
                '}';
    }
}

