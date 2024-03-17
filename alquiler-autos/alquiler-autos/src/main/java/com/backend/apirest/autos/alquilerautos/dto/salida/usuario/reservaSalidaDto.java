package com.backend.apirest.autos.alquilerautos.dto.salida.usuario;

import com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo.VehiculoSalidaDto;

import java.util.List;

public class reservaSalidaDto {
    private Long id;
    private Long idVehiculo;
    private Long idUsuario;
    public reservaSalidaDto() {
    }

    public reservaSalidaDto(Long id, Long idVehiculo, Long idUsuario) {
        this.id = id;
        this.idVehiculo = idVehiculo;
        this.idUsuario = idUsuario;
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
}

