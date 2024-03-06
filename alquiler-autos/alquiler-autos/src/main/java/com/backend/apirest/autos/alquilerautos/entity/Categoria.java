package com.backend.apirest.autos.alquilerautos.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CATEGORIAS")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categorias")
    private Long id;

    private String titulo;
    private String descripcion;
    @Column(name = "url_image")
    private String urlImage;

    @OneToMany(mappedBy = "categoria")
    private Set<Vehiculo> vehiculos;

    public Categoria() {
    }

    public Categoria(Long id, String titulo, String descripcion, String urlImage, Set<Vehiculo> vehiculos) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.urlImage = urlImage;
        this.vehiculos = vehiculos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Set<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(Set<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
}

