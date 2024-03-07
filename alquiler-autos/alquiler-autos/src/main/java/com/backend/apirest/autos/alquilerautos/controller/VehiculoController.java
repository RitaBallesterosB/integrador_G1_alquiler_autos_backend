package com.backend.apirest.autos.alquilerautos.controller;

import com.backend.apirest.autos.alquilerautos.dto.entrada.vehiculo.ImagenEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.entrada.vehiculo.VehiculoEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo.VehiculoSalidaDto;
import com.backend.apirest.autos.alquilerautos.exceptions.BadRequestException;
import com.backend.apirest.autos.alquilerautos.service.IVehiculoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    private final IVehiculoService vehiculoService;

    @Autowired
    public VehiculoController(IVehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    // Método para agregar un vehículo
    //____________________________________________________________________________________
    @PostMapping("/agregar")
    public ResponseEntity<VehiculoSalidaDto> agregarVehiculo(@Valid @RequestBody VehiculoEntradaDto vehiculo)
            throws BadRequestException {
        return new ResponseEntity<>(vehiculoService.agregarVehiculo(vehiculo), HttpStatus.CREATED);
    }
    @PostMapping("/{id}/agregar-imagenes")
    public ResponseEntity<?> agregarImagenesAVehiculo(
            @PathVariable Long id,
            @Valid @RequestBody List<ImagenEntradaDto> nuevasImagenes) {
        VehiculoSalidaDto vehiculoActualizado = vehiculoService.agregarImagenesAVehiculo(id, nuevasImagenes);
        if (vehiculoActualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso no encontrado");
        }
        return ResponseEntity.ok(vehiculoActualizado);
    }

//___________________________________________________________________________________
// Método para obtener vehiculos aleatorios

    @GetMapping("/aleatorios")
    public ResponseEntity<List<VehiculoSalidaDto>> obtenerVehiculosAleatorios() {
        List<VehiculoSalidaDto> vehiculosAleatorios = vehiculoService.obtenerVehiculosAleatorios();
        return ResponseEntity.ok(vehiculosAleatorios);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarVehiculos() {
        try{
            List<VehiculoSalidaDto> vehiculos = vehiculoService.listarVehiculos();
            return new ResponseEntity<>(vehiculos, HttpStatus.OK);
        }catch (Exception e){
            // Manejo de excepciones aquí, puedes imprimir el error en la consola o manejarlo según tus necesidades
            e.printStackTrace();
            return new ResponseEntity<>("Error al obtener la lista de vehículos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método DELETE para eliminar un vehículo por su ID
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarVehiculo(@PathVariable Long id) {
        try {
            vehiculoService.eliminarVehiculo(id);
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode jsonResponse = mapper.createObjectNode();
            jsonResponse.put("mensaje", "Vehículo eliminado exitosamente");
            return ResponseEntity.ok(jsonResponse);
        } catch (NoSuchElementException e) {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode jsonResponse = mapper.createObjectNode();
            jsonResponse.put("mensaje", "Vehículo no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonResponse);
        }
    }

}