package com.backend.apirest.autos.alquilerautos.dto.entrada.vehiculo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImagenEntradaDto {
    @NotNull(message = "El nombre de la imagen no debe estar nulo")
    @NotBlank(message = "El nombre de la imagen no debe estar en blanco")
    private String nombre;

    @NotNull(message = "La URL de la imagen no debe estar nula")
    @NotBlank(message = "La URL de la imagen no debe estar en blanco")
    @Size(min = 2, max = 1000, message = "La URL de la imagen debe tener entre 2 y 1000 caracteres")
    private String url;

    public ImagenEntradaDto() {
    }

    public ImagenEntradaDto(String nombre, String url) {
        this.nombre = nombre;
        this.url = url;
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
}



