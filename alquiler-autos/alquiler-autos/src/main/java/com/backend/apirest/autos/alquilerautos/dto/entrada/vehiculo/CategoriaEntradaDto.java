package com.backend.apirest.autos.alquilerautos.dto.entrada.vehiculo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoriaEntradaDto {
    @Size(min = 2, max = 200, message = "El titulo  debe tener hasta 200 caracteres")

    @NotNull(message ="El titulo no debe estar nulo")

        private String titulo;
    @Size(min = 2, max = 1000, message = "Descripción debe tener hasta 1000 caracteres")

    @NotNull(message ="Descripción no debe estar nulo")
        private String descripcion;

    @Size(min = 2, max = 2000, message = "La url  debe tener hasta 2000 caracteres")

    @NotNull(message ="La url no debe estar nula")
        private String urlImage;

    public CategoriaEntradaDto() {
    }

    public CategoriaEntradaDto(String titulo, String descripcion, String urlImage) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.urlImage = urlImage;
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
}

