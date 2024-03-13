package com.backend.apirest.autos.alquilerautos.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categorias")
    private Long id;

    private String titulo;
    private String descripcion;
    @Column(name = "url_imagen")
    private String urlImagen;

    @OneToMany(mappedBy = "categoria")
    private Set<Vehiculo> vehiculos;

    public Categoria() {
    }

    public Categoria(Long id, String titulo, String descripcion, String urlImage, Set<Vehiculo> vehiculos) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
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

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Set<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(Set<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
}