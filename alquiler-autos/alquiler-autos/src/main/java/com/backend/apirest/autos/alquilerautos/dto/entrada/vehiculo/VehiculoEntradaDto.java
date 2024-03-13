package com.backend.apirest.autos.alquilerautos.dto.entrada.vehiculo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VehiculoEntradaDto {

    @NotBlank(message = "El nombre del Vehiculo es Obligatorio")
    @NotNull(message = "El nombre del vehiculo no puede ser nulo")
    private String nombre;


    @NotNull(message = "Descripción No debe estar nulo")
    @NotBlank(message = "La descripción  del Vehiculo es Obligatoria")
    private String descripcion;

    private List<ImagenEntradaDto> imagenes;
    private CategoriaEntradaDto categoria;



    public VehiculoEntradaDto() {
    }

    public VehiculoEntradaDto(String nombre, String descripcion, List<ImagenEntradaDto> imagenes, CategoriaEntradaDto categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
        this.categoria = categoria;
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

    public CategoriaEntradaDto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntradaDto categoria) {
        this.categoria = categoria;
    }
}

