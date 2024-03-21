package com.backend.apirest.autos.alquilerautos.dto.salida.usuario;

import java.util.Date;

public class ReservaSalidaDto {
    private Long id;
    private Long idVehiculo;
    private Long idUsuario;
    private Date fechaEntrega;
    private Date fechaDevolucion;

    public ReservaSalidaDto() {
    }

    public ReservaSalidaDto(Long id, Long idVehiculo, Long idUsuario, Date fechaEntrega, Date fechaDevolucion) {
        this.id = id;
        this.idVehiculo = idVehiculo;
        this.idUsuario = idUsuario;
        this.fechaEntrega = fechaEntrega;
        this.fechaDevolucion = fechaDevolucion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}

