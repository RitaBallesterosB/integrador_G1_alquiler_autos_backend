package com.backend.apirest.autos.alquilerautos.dto.entrada.usuario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservaEntradaDto {
    private Long vehiculoId;
    private Long usuarioId;
    private Date fechaEntrega;
    private Date fechaDevolucion;
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String telefono;
    private Long metodoPago;

    // Constructor vacío
    public ReservaEntradaDto() {
    }

    // Constructor con todos los campos


    public ReservaEntradaDto(Long vehiculoId, Long usuarioId, Date fechaEntrega, Date fechaDevolucion, String nombre, String apellido, String correoElectronico, String telefono, Long metodoPago) {
        this.vehiculoId = vehiculoId;
        this.usuarioId = usuarioId;
        this.fechaEntrega = fechaEntrega;
        this.fechaDevolucion = fechaDevolucion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.metodoPago = metodoPago;
    }

    public Long getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Long vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(Long metodoPago) {
        this.metodoPago = metodoPago;
    }
}



