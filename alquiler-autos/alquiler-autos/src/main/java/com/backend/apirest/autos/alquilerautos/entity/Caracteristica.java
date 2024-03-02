package com.backend.apirest.autos.alquilerautos.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CARACTERISTICAS")
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="id_caracteristicas")
    private Long id;
    private String nombre;
    @Column(name="url_icono")
    private String urlIcono;

    @OneToMany(mappedBy = "caracteristica")
    private Set<VehiculoCaracteristica> vehiculoCaracteristicas;

    public Caracteristica() {
    }

    public Caracteristica(Long id, String nombre, String urlIcono, Set<VehiculoCaracteristica> vehiculoCaracteristicas) {
        this.id = id;
        this.nombre = nombre;
        this.urlIcono = urlIcono;
        this.vehiculoCaracteristicas = vehiculoCaracteristicas;
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

    public String getUrlIcono() {
        return urlIcono;
    }

    public void setUrlIcono(String urlIcono) {
        this.urlIcono = urlIcono;
    }

    public Set<VehiculoCaracteristica> getVehiculoCaracteristicas() {
        return vehiculoCaracteristicas;
    }

    public void setVehiculoCaracteristicas(Set<VehiculoCaracteristica> vehiculoCaracteristicas) {
        this.vehiculoCaracteristicas = vehiculoCaracteristicas;
    }
}
