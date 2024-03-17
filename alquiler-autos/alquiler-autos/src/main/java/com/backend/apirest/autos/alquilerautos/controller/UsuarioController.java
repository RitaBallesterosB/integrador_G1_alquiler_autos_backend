package com.backend.apirest.autos.alquilerautos.controller;

import com.backend.apirest.autos.alquilerautos.dto.entrada.usuario.UsuarioEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.usuario.GestionUsuarioSalidaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.usuario.UsuarioSalidaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo.VehiculoSalidaDto;
import com.backend.apirest.autos.alquilerautos.entity.Usuario;
import com.backend.apirest.autos.alquilerautos.exceptions.ResponseJson;
import com.backend.apirest.autos.alquilerautos.exceptions.UsuarioNotFoundException;
import com.backend.apirest.autos.alquilerautos.repository.UsuarioRepository;
import com.backend.apirest.autos.alquilerautos.service.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequestMapping("/usuarios")
@RestController
public class UsuarioController {
    public ResponseJson responseJson;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @PostMapping("/registro")
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

    @GetMapping("/listar")
    public ResponseEntity<?> listarVehiculos() {
        try{
            List<GestionUsuarioSalidaDto> usuarios = usuarioService.listarUsuarios();
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        }catch (Exception e){
            // Manejo de excepciones aquí, puedes imprimir el error en la consola o manejarlo según tus necesidades
            e.printStackTrace();
            return new ResponseEntity<>("Error al obtener la lista de vehículos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/permisos/{idUsuario}")
    public ResponseEntity<ResponseJson> actualizarPermisosUsuario(
            @PathVariable Long idUsuario,
            @RequestBody Map<String, Integer> requestBody) {
            Integer administrador = requestBody.get("administrador");
        try {

            // Llama al servicio para actualizar los permisos del usuario
            GestionUsuarioSalidaDto usuarioActualizado = usuarioService.actualizarPermisosUsuario(idUsuario, administrador);
            Map<String, Object> userData = new HashMap<>();
            userData.put("idUsuario", usuarioActualizado.getIdUsuario());
            userData.put("nombre", usuarioActualizado.getNombre());
            userData.put("apellido", usuarioActualizado.getApellido());
            userData.put("correoElectronico", usuarioActualizado.getCorreoElectronico());
            userData.put("administrador", usuarioActualizado.getAdministrador());
            // Devuelve la respuesta con los datos del usuario
            ResponseJson responseData = new ResponseJson(true, "Usuario actualizado", userData);
            return ResponseEntity.ok(responseData);
        } catch (UsuarioNotFoundException e) {
            // Manejo de excepción si el usuario no es encontrado
            ResponseJson responseData = new ResponseJson(false, "Usuario no encontrado", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
    }
}
















