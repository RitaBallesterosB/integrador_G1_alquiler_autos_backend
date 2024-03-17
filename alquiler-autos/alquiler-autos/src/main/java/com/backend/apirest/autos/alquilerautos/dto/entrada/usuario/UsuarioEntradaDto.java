package com.backend.apirest.autos.alquilerautos.dto.entrada.usuario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioEntradaDto {
    @NotNull(message = "El nombre del usuario no debe estar nulo")
    @NotBlank(message = "El nombre del usuario no debe estar en blanco")
    private String nombre;

    @NotNull(message = "El apellido del usuario no debe estar nulo")
    @NotBlank(message = "Elapellido del usuario no debe estar en blanco")
    private String apellido;
    @NotNull(message = "El correo Electrónico del usuario no debe estar nulo")
    @NotBlank(message = "El correo Electrónico del usuario no debe estar en blanco")
    @Size(min = 2, max = 45, message = "El correo debe tener entre 2 y 45 caracteres")
    private String correoElectronico;
    @NotNull(message = "La contraseña del usuario no debe estar nulo")
    @NotBlank(message = "La contraseña del usuario no debe estar en blanco")
    @Size(min = 2, max = 45, message = "La contraseña del usuario debe tener entre 2 y 45 caracteres")
    private String contrasenia;
    // Constructor vacío
    public UsuarioEntradaDto() {
    }

    // Constructor con todos los campos
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



