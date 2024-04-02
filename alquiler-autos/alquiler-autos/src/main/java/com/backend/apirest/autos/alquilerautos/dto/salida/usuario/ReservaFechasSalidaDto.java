package com.backend.apirest.autos.alquilerautos.dto.salida.usuario;

import java.util.Date;

public class ReservaFechasSalidaDto {
    private Date fechaEntrega;
    private Date fechaDevolucion;
    public ReservaFechasSalidaDto() {
    }

    public ReservaFechasSalidaDto(Date fechaEntrega, Date fechaDevolucion) {
        this.fechaEntrega = fechaEntrega;
        this.fechaDevolucion = fechaDevolucion;
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

