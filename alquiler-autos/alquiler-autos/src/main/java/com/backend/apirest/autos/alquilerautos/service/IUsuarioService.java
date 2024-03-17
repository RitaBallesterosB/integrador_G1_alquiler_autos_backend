package com.backend.apirest.autos.alquilerautos.service;

import com.backend.apirest.autos.alquilerautos.dto.salida.usuario.UsuarioSalidaDto;
import com.backend.apirest.autos.alquilerautos.repository.UsuarioRepository;

import java.util.List;

public interface IUsuarioService {
    public String autenticarUsuario(String correoElectronico, String contrasenia);

    public List<UsuarioSalidaDto> listarUsuarios();
}
