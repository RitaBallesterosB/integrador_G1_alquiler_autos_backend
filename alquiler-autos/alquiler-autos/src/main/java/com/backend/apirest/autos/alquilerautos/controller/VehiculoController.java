package com.backend.apirest.autos.alquilerautos.controller;

import com.backend.apirest.autos.alquilerautos.dto.entrada.vehiculo.ImagenEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.entrada.vehiculo.VehiculoEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo.VehiculoSalidaDto;
import com.backend.apirest.autos.alquilerautos.entity.Vehiculo;
import com.backend.apirest.autos.alquilerautos.exceptions.BadRequestException;
import com.backend.apirest.autos.alquilerautos.exceptions.ResponseJson;
import com.backend.apirest.autos.alquilerautos.exceptions.VehiculoException;
import com.backend.apirest.autos.alquilerautos.service.IVehiculoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    private final IVehiculoService vehiculoService;

    @Autowired
    public VehiculoController(IVehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    //____________________________________________________________________________________
    // Método para agregar un vehículo
    @PostMapping("/agregar")
    public ResponseEntity<?> agregarVehiculo(@Valid @RequestBody VehiculoEntradaDto vehiculo) {
        try {
            if (vehiculo == null || vehiculo.getNombre() == null || vehiculo.getNombre().isEmpty()) {
                throw new BadRequestException("El campo nombre no puede estar vacío");
            }
            VehiculoSalidaDto vehiculoSalidaDto = vehiculoService.agregarVehiculo(vehiculo);
            return ResponseEntity.ok(vehiculoSalidaDto);
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }





    //____________________________________________________________________________________
    // Método para agregar imágenes
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

    //____________________________________________________________________________________
    // Método para obtener un vehículo por su ID con imágenes
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerVehiculoPorIdConImagenes(@PathVariable Long id) {
        try {
            VehiculoSalidaDto vehiculo = vehiculoService.obtenerVehiculoPorIdConImagenes(id);
            if (vehiculo == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehículo no encontrado");
            }
            return ResponseEntity.ok(vehiculo);
        } catch (VehiculoException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener el vehículo");
        }
    }
    //___________________________________________________________________________________
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
    //___________________________________________________________________________________
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
   //Metodo de buscar vehiculos
   @GetMapping("/busqueda")
   public ResponseEntity<?> buscarVehiculos(@RequestBody(required = false) Map<String, Object> requestBody) throws ParseException {
       try {
           if (requestBody == null || requestBody.isEmpty()) {
               // Manejar el caso en el que no se proporciona ningún parámetro en el cuerpo
               // Puedes devolver una lista vacía o un mensaje de error, dependiendo de tu lógica de negocio
               return ResponseEntity.ok(Collections.emptyList());
           }

           // Extrae los parámetros del cuerpo de la solicitud
           String consulta = (String) requestBody.get("consulta");
           List<Long> categoria = new ArrayList<>();

           if (requestBody.containsKey("categoria") && requestBody.get("categoria") != null) {
               categoria = ((List<Integer>) requestBody.get("categoria")).stream()
                       .map(Long::valueOf)
                       .collect(Collectors.toList());
           }
           String fechaInicial = (String) requestBody.get("fechaInicial");
           String fechaFinal = (String) requestBody.get("fechaFinal");

           List<VehiculoSalidaDto> vehiculos = vehiculoService.buscarVehiculos(consulta, categoria, fechaInicial, fechaFinal);
           //System.out.println(vehiculos);
           return new ResponseEntity<>(vehiculos, HttpStatus.OK);
       } catch (HttpMessageNotWritableException e) {
           // Manejar la excepción aquí (p. ej., registrarla, devolver un mensaje de error personalizado, etc.)
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al serializar la respuesta JSON");
       }
   }

    @GetMapping("/busqueda/sugerencias")
    public ResponseEntity<ResponseJson> obtenerSugerencias(@RequestBody Map<String, Object> requestBody) {
        Map<String, Object> nameVehiculos = new HashMap<>();
        String consulta = (String) requestBody.get("consulta");
        List<String> nombresVehiculos = vehiculoService.obtenerSugerencias(consulta);
        nameVehiculos.put("sugerencias", nombresVehiculos);
        // Devuelve la respuesta con los datos del usuario
        ResponseJson responseData = new ResponseJson(true, "Sugerencias encontradas", nameVehiculos);
        return ResponseEntity.ok(responseData);

    }
}