package com.backend.apirest.autos.alquilerautos.controller;

import com.backend.apirest.autos.alquilerautos.dto.entrada.vehiculo.CategoriaEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo.CategoriaSalidaDto;
import com.backend.apirest.autos.alquilerautos.service.ICategoriaService;
import com.backend.apirest.autos.alquilerautos.exceptions.BadRequestException;
import com.backend.apirest.autos.alquilerautos.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final ICategoriaService categoriaService;

    @Autowired
    public CategoriaController(ICategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> agregarCategoria(@Valid @RequestBody CategoriaEntradaDto categoriaDto, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        try {
            CategoriaSalidaDto categoriaGuardada = categoriaService.agregarCategoria(categoriaDto);
            return new ResponseEntity<>(categoriaGuardada, HttpStatus.CREATED);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/vehiculo/{idVehiculo}/categoria/{idCategoria}")
    public ResponseEntity<?> asignarCategoriaAVehiculo(@PathVariable Long idVehiculo, @PathVariable Long idCategoria) {
        try {
            categoriaService.asignarCategoriaAVehiculo(idVehiculo, idCategoria);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/vehiculo/{idVehiculo}/categoria/{idCategoria}")
    public ResponseEntity<?> cambiarCategoriaDeVehiculo(@PathVariable Long idVehiculo, @PathVariable Long idCategoria) {
        try {
            categoriaService.cambiarCategoriaDeVehiculo(idVehiculo, idCategoria);
            String mensaje = "Se ha cambiado la categoría del vehículo " + idVehiculo + " a la categoría con ID " + idCategoria + " correctamente.";
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}












