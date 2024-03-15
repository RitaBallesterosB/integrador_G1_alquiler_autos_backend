package com.backend.apirest.autos.alquilerautos.controller;

import com.backend.apirest.autos.alquilerautos.dto.entrada.usuario.UsuarioEntradaDto;
import com.backend.apirest.autos.alquilerautos.service.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios/registro")
    public ResponseEntity<String> registrarUsuario(@RequestBody UsuarioEntradaDto usuarioDto) {
        System.out.println("Solicitud recibida para registrar usuario: " + usuarioDto);
        usuarioService.registrarUsuario(usuarioDto);
        System.out.println("Usuario registrado exitosamente: " + usuarioDto);
        return ResponseEntity.ok("Usuario registrado exitosamente");
    }

    @PostMapping("/administrar")
    public ResponseEntity<Void> administrarUsuario(@RequestParam Long idUsuario, @RequestParam boolean isAdmin) {
        usuarioService.administrarUsuario(idUsuario, isAdmin);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

