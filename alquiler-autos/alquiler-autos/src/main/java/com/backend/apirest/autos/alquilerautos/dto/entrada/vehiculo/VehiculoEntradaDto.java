package com.backend.apirest.autos.alquilerautos.dto.entrada.vehiculo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VehiculoEntradaDto {
    @Size(min = 2, max = 150, message = "El nombre debe tener hasta 150 caracteres")
    @NotNull(message = "El nombre del Vehiculo es Obligatorio")
    private String nombre;

    @Size(min = 2, max = 150, message = "Descripción debe tener hasta 150 caracteres")
    @NotNull(message = "Descripción No debe estar nulo")
    private String descripcion;

    private List<ImagenEntradaDto> imagenes;

    public VehiculoEntradaDto() {
    }

    public VehiculoEntradaDto(String nombre, String descripcion, List<ImagenEntradaDto> imagenes) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
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

    public List<ImagenEntradaDto> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<ImagenEntradaDto> imagenes) {
        this.imagenes = imagenes;
    }
}

