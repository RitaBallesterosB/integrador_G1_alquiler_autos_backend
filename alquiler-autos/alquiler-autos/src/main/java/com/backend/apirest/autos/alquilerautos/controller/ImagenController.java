package com.backend.apirest.autos.alquilerautos.controller;


import com.backend.apirest.autos.alquilerautos.dto.entrada.vehiculo.ImagenEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo.ImagenSalidaDto;
import com.backend.apirest.autos.alquilerautos.service.ImpImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/imagenes")
    public class ImagenController {

        private final ImpImagenService imagenService;


        @Autowired
        public ImagenController(ImpImagenService imagenService) {
            this.imagenService = imagenService;
        }

        @PostMapping("/agregar")
        public ResponseEntity<ImagenSalidaDto> agregarImagen(@Valid @RequestBody ImagenEntradaDto imagen) {
            return new ResponseEntity<>(imagenService.agregarImagen(imagen), HttpStatus.CREATED);
        }

        @GetMapping("/galeria/{vehiculoId}")
        public ResponseEntity<?> listarGaleriaImagenes(@PathVariable Long vehiculoId) {
            try{
                List<ImagenSalidaDto> imagenes = imagenService.listarGaleriaImagenes(vehiculoId);
                return new ResponseEntity<>(imagenes, HttpStatus.OK);
            }catch (Exception e){
                // Manejo de excepciones aquí, puedes imprimir el error en la consola o manejarlo según tus necesidades
                e.printStackTrace();
                return new ResponseEntity<>("Error al obtener galeria de imagenes", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @GetMapping("/galeria/{vehiculoId}/vermas")
        public ResponseEntity<?> listarGaleriaImagenesVerMas(@PathVariable Long vehiculoId) {
            try{
                List<ImagenSalidaDto> imagenes = imagenService.listarGaleriaImagenesVerMas(vehiculoId);
                return new ResponseEntity<>(imagenes, HttpStatus.OK);
            }catch (Exception e){
                // Manejo de excepciones aquí, puedes imprimir el error en la consola o manejarlo según tus necesidades
                e.printStackTrace();
                return new ResponseEntity<>("Error al obtener imagenes", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }


