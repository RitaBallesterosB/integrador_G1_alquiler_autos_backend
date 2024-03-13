package com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo;



public class CategoriaSalidaDto {

    private Long id;
    private String titulo;

    private String descripcion;


    private String urlImagen;

    public CategoriaSalidaDto() {
    }

    public CategoriaSalidaDto(Long id, String titulo, String descripcion, String urlImagen) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
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

    public void setUrlImagen(String urlImage) {
        this.urlImagen = urlImage;
    }

    @Override
    public String toString() {
        return "id:" + id +  ", titulo:'" + titulo + '\'' + ", descripcion:'" + descripcion + '\'' + ", urlImagen:'" + urlImagen + '\'' + '}';
    }
}
