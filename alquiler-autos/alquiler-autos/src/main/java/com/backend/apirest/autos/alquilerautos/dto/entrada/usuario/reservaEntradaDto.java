package com.backend.apirest.autos.alquilerautos.dto.entrada.usuario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class reservaEntradaDto {
    @NotNull(message = "El idVehiculo no debe estar nulo")
    @NotBlank(message = "El idVehiculo no debe estar en blanco")
    private Long idVehiculo;
    @NotNull(message = "El idUsuario no debe estar nulo")
    @NotBlank(message = "El idUsuario no debe estar en blanco")
    private Long idUsuario;

    private Date fechaEntrega;

    private Date fechaDevolucion;

    // Constructor vacío
    public reservaEntradaDto() {
    }

    // Constructor con todos los campos
    public reservaEntradaDto(Long idVehiculo, Long idUsuario, Date fechaEntrega, Date fechaDevolucion) {
        this.idVehiculo = idVehiculo;
        this.idUsuario = idUsuario;
        this.fechaEntrega = fechaEntrega;
        this.fechaDevolucion = fechaDevolucion;
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



