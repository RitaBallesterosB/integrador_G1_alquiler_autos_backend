package com.backend.apirest.autos.alquilerautos.controller;

import com.backend.apirest.autos.alquilerautos.dto.entrada.usuario.UsuarioEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.usuario.UsuarioSalidaDto;
import com.backend.apirest.autos.alquilerautos.entity.Usuario;
import com.backend.apirest.autos.alquilerautos.exceptions.UsuarioNotFoundException;
import com.backend.apirest.autos.alquilerautos.repository.UsuarioRepository;
import com.backend.apirest.autos.alquilerautos.service.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;



@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }



    @PostMapping("/usuarios/registro")
    public ResponseEntity<UsuarioSalidaDto> registrarUsuario(@RequestBody UsuarioEntradaDto usuarioDto) {
        // Registra el usuario y obtén su ID
        Long idUsuarioRegistrado = usuarioService.registrarUsuario(usuarioDto);

        // Construye el UsuarioSalidaDto con el ID del usuario registrado y los datos proporcionados en el request
        UsuarioSalidaDto usuarioSalidaDto = new UsuarioSalidaDto();
        usuarioSalidaDto.setIdUsuario(idUsuarioRegistrado);
        usuarioSalidaDto.setNombre(usuarioDto.getNombre());
        usuarioSalidaDto.setApellido(usuarioDto.getApellido());
        usuarioSalidaDto.setCorreoElectronico(usuarioDto.getCorreoElectronico());

        // Retorna el UsuarioSalidaDto
        return ResponseEntity.ok(usuarioSalidaDto);
    }

}
















