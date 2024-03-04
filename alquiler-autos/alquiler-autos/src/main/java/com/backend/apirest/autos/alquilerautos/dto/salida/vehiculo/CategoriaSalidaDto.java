package com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoriaSalidaDto {

    private Long id;
    private String titulo;

    private String descripcion;


    private String urlImage;

    public CategoriaSalidaDto() {
    }

    public CategoriaSalidaDto(Long id, String titulo, String descripcion, String urlImage) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.urlImage = urlImage;
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

    @Override
    public String toString() {
        return "id:" + id +  ", titulo:'" + titulo + '\'' + ", descripcion:'" + descripcion + '\'' + ", urlImage:'" + urlImage + '\'' + '}';
    }
}
