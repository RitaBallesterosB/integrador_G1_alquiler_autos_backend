package com.backend.apirest.autos.alquilerautos.controller;
import com.backend.apirest.autos.alquilerautos.dto.AuthenticationResponse;
import com.backend.apirest.autos.alquilerautos.dto.LoginRequest;
import com.backend.apirest.autos.alquilerautos.service.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        String jwt = usuarioService.autenticarUsuario(loginRequest.getCorreoElectronico(), loginRequest.getContrasenia());
        return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
    }
}
