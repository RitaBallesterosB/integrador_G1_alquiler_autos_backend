package com.backend.apirest.autos.alquilerautos.dto.salida.usuario;

import com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo.VehiculoSalidaDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class UsuarioSalidaDto {
    private Long idUsuario;
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private Integer administrador;
    private List<reservaSalidaDto> reservas;
    public UsuarioSalidaDto() {
    }

    public UsuarioSalidaDto(Long idUsuario, String nombre, String apellido, String correoElectronico, Integer administrador, List<reservaSalidaDto> reservas) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.administrador = administrador;
        this.reservas = reservas;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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

    public Integer getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Integer administrador) {
        this.administrador = administrador;
    }

    public List<reservaSalidaDto> getReservas() {
        return reservas;
    }

    public void setReservas(List<reservaSalidaDto> reservas) {
        this.reservas = reservas;
    }

    @Override
    public String toString() {
        return "UsuarioSalidaDto{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", administrador=" + administrador +
                ", reservas=" + reservas +
                '}';
    }
}

