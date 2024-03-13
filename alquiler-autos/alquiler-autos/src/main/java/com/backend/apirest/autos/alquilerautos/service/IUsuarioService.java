package com.backend.apirest.autos.alquilerautos.service;

import com.backend.apirest.autos.alquilerautos.repository.UsuarioRepository;

public interface IUsuarioService {
    public String autenticarUsuario(String correoElectronico, String contrasenia);
}
