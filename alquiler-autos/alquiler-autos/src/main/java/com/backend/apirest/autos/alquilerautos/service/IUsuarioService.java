package com.backend.apirest.autos.alquilerautos.service;


import com.backend.apirest.autos.alquilerautos.dto.entrada.usuario.UsuarioEntradaDto;
import com.backend.apirest.autos.alquilerautos.entity.Usuario;

public interface IUsuarioService {
    public String autenticarUsuario(String correoElectronico, String contrasenia);

    //__________________________________________________________________________
    public void registrarUsuario(UsuarioEntradaDto usuarioDto);
    void administrarUsuario(Long idUsuario, boolean isAdmin);
}
