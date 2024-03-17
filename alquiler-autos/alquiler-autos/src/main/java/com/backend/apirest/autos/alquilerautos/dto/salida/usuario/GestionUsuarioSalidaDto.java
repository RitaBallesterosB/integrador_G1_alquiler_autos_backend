package com.backend.apirest.autos.alquilerautos.dto.salida.usuario;


public class GestionUsuarioSalidaDto {
    private Long idUsuario;
    private String nombre;
    private String apellido;
    private String correoElectronico;

    private Integer administrador;

    public GestionUsuarioSalidaDto() {
    }

    public GestionUsuarioSalidaDto(Long idUsuario, String nombre, String apellido, String correoElectronico, Integer administrador) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.administrador = administrador;
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

    @Override
    public String toString() {
        return "GestionUsuarioSalidaDto{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", administrador=" + administrador +
                '}';
    }
}

