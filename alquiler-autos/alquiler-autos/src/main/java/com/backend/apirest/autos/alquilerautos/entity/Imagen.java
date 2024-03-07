package com.backend.apirest.autos.alquilerautos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "imagenes")
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_imagenes")
    private Long id;

    private String nombre;
    private String url;

    @ManyToOne
    @JoinColumn(name= "VEHICULOS_id_vehiculos")
    @JsonIgnore
    private Vehiculo vehiculo;

    public Imagen() {
    }

    public Imagen(Long id, String nombre, String url, Vehiculo vehiculo) {
        this.id = id;
        this.nombre = nombre;
        this.url = url;
        this.vehiculo = vehiculo;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
}
