package com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo;

import java.util.List;

public class VehiculoSalidaDto {

    private Long id;
    private String nombre;
    private String descripcion;
    private List<ImagenSalidaDto> imagenes;

    public VehiculoSalidaDto() {
    }

    public VehiculoSalidaDto(Long id, String nombre, String descripcion, List<ImagenSalidaDto> imagenes) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
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

    public List<ImagenSalidaDto> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<ImagenSalidaDto> imagenes) {
        this.imagenes = imagenes;
    }
    @Override
    public String toString() { return "id:" + id + ", Nombre: " + nombre + '\'' + ", Descripcionn: " + descripcion + '\'' + ", Imagenes=" + imagenes; }
}

