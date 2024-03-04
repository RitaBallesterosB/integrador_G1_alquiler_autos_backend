package com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo;

public class ImagenSalidaDto {
    private String nombre;
    private String url;

    public ImagenSalidaDto() {
    }

    public ImagenSalidaDto(String nombre, String url) {
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
    @Override
    public String toString() { return "nombre:" + nombre  + '\'' + ", url=" + url; }
}

