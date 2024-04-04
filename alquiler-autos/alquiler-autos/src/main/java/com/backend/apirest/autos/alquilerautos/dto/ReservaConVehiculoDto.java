package com.backend.apirest.autos.alquilerautos.dto;

import com.backend.apirest.autos.alquilerautos.dto.salida.usuario.UsuarioSalidaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo.VehiculoSalidaDto;

import java.util.Date;

public class ReservaConVehiculoDto {
    private Long id;
    private Date fechaEntrega;
    private Date fechaDevolucion;

    private VehiculoSalidaDto vehiculo;

    private UsuarioSalidaDto usuario;

    public ReservaConVehiculoDto() {
    }

    public ReservaConVehiculoDto(Long id, Date fechaEntrega, Date fechaDevolucion, VehiculoSalidaDto vehiculo, UsuarioSalidaDto usuario) {
        this.id = id;
        this.fechaEntrega = fechaEntrega;
        this.fechaDevolucion = fechaDevolucion;
        this.vehiculo = vehiculo;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public VehiculoSalidaDto getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoSalidaDto vehiculo) {
        this.vehiculo = vehiculo;
    }

    public UsuarioSalidaDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioSalidaDto usuario) {
        this.usuario = usuario;
    }
}
