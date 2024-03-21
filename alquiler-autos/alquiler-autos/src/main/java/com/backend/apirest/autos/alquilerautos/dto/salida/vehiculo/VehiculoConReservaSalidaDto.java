package com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo;



import com.backend.apirest.autos.alquilerautos.dto.salida.usuario.ReservaSalidaDto;

import java.util.List;

public class VehiculoConReservaSalidaDto {

    private Long id;
    private String nombre;
    private String descripcion;

    private List<ImagenSalidaDto> imagenes;
    private CategoriaSalidaDto categoria;

    private List<ReservaSalidaDto> reservas;

    public VehiculoConReservaSalidaDto() {
    }

    public VehiculoConReservaSalidaDto(Long id, String nombre, String descripcion, List<ImagenSalidaDto> imagenes, CategoriaSalidaDto categoria, List<ReservaSalidaDto> reservas) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
        this.categoria = categoria;
        this.reservas = reservas;
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

    public CategoriaSalidaDto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaSalidaDto categoria) {
        this.categoria = categoria;
    }

    public List<ReservaSalidaDto> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaSalidaDto> reservas) {
        this.reservas = reservas;
    }

    @Override
    public String toString() {
        return "VehiculoConReservaSalidaDto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagenes=" + imagenes +
                ", categoria=" + categoria +
                ", reservas=" + reservas +
                '}';
    }
}



