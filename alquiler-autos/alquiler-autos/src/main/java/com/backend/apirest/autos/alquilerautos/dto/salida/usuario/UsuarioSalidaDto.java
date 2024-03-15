package com.backend.apirest.autos.alquilerautos.dto.salida.usuario;

public class UsuarioSalidaDto {
    private Long idUsuario;
    private String mensaje;

    public UsuarioSalidaDto() {
    }

    public UsuarioSalidaDto(Long idUsuario, String mensaje) {
        this.idUsuario = idUsuario;
        this.mensaje = mensaje;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "UsuarioSalidaDto{" +
                "idUsuario=" + idUsuario +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }
}
