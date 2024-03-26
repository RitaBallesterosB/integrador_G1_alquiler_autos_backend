package com.backend.apirest.autos.alquilerautos.service;

import com.backend.apirest.autos.alquilerautos.dto.salida.usuario.GestionUsuarioSalidaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.usuario.UsuarioSalidaDto;
import com.backend.apirest.autos.alquilerautos.repository.UsuarioRepository;
import com.backend.apirest.autos.alquilerautos.dto.entrada.usuario.UsuarioEntradaDto;
import com.backend.apirest.autos.alquilerautos.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
    public String autenticarUsuario(String correoElectronico, String contrasenia);
    public List<GestionUsuarioSalidaDto> listarUsuarios();
    //__________________________________________________________________________
    public Long registrarUsuario(UsuarioEntradaDto usuarioDto);
    void administrarUsuario(Long idUsuario, boolean isAdmin);

    public List<GestionUsuarioSalidaDto> obtenerUsuarioPorEmail(String correoElectronico);
}
