package com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ImagenSalidaDto {
    private String nombre;
    private String url;
    @JsonIgnore
    private VehiculoSalidaDto vehiculo;
    public ImagenSalidaDto() {
    }

    public ImagenSalidaDto(String nombre, String url,VehiculoSalidaDto vehiculo) {
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

    public VehiculoSalidaDto getVehiculo() {
        return vehiculo;
    }
    public void setVehiculo(VehiculoSalidaDto vehiculo) {
        this.vehiculo = vehiculo;
    }
    @Override
    public String toString() { return "nombre:" + nombre  + '\'' + ", url=" + url;}
}

