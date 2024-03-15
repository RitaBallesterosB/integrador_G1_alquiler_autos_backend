package com.backend.apirest.autos.alquilerautos.dto.entrada.usuario;

public class UsuarioEntradaDto {
        private String nombre;
        private String apellido;
        private String correoElectronico;
        private String contrasenia;

    public UsuarioEntradaDto() {
    }

    public UsuarioEntradaDto(String nombre, String apellido, String correoElectronico, String contrasenia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
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

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
