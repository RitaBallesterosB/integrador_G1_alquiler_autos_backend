package com.backend.apirest.autos.alquilerautos.controller;

import com.backend.apirest.autos.alquilerautos.dto.entrada.vehiculo.CategoriaEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo.CategoriaSalidaDto;
import com.backend.apirest.autos.alquilerautos.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private final ICategoriaService categoriaService;

    @Autowired
    public CategoriaController(ICategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping("/agregar")
    public ResponseEntity<CategoriaSalidaDto> agregarCategoria(@RequestBody CategoriaEntradaDto categoriaDto) {
        CategoriaSalidaDto categoriaAgregada = categoriaService.agregarCategoria(categoriaDto);
        return new ResponseEntity<>(categoriaAgregada, HttpStatus.CREATED);
    }

}

