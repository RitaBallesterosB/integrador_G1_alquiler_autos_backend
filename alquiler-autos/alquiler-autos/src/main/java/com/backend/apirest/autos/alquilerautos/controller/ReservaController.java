package com.backend.apirest.autos.alquilerautos.controller;

import com.backend.apirest.autos.alquilerautos.dto.entrada.usuario.ReservaEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.entrada.usuario.UsuarioEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.usuario.ReservaSalidaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.usuario.UsuarioSalidaDto;
import com.backend.apirest.autos.alquilerautos.exceptions.BadRequestException;
import com.backend.apirest.autos.alquilerautos.exceptions.ResponseJson;
import com.backend.apirest.autos.alquilerautos.service.IReservaService;
import com.backend.apirest.autos.alquilerautos.service.impl.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/reservas")
@RestController
public class ReservaController {
    @Autowired
    private ReservaService reservaService;
    @Autowired
    public ReservaController(ReservaService reservaService) {this.reservaService = reservaService;}

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarReserva(@Valid @RequestBody ReservaEntradaDto reservaDto) {
        System.out.println(reservaService.convertirDtoAReserva(reservaDto).toString());
        try {
            if (reservaDto == null || reservaDto.getNombre() == null || reservaDto.getNombre().isEmpty()||reservaDto.getApellido() == null || reservaDto.getApellido().isEmpty()
                    || reservaDto.getCorreoElectronico() == null || reservaDto.getCorreoElectronico().isEmpty() || reservaDto.getFechaEntrega() == null || reservaDto.getFechaDevolucion()==null ) {
                throw new BadRequestException("Los campos obligatorios no pueden estar vacíos");
            }
            Long idReservaRegistrada = reservaService.registrarReserva(reservaDto);

            ReservaSalidaDto reservaSalidaDto = new ReservaSalidaDto();
            reservaSalidaDto.setId(idReservaRegistrada);
            reservaSalidaDto.setUsuarioId(reservaDto.getUsuarioId());
            reservaSalidaDto.setVehiculoId(reservaDto.getVehiculoId());
            reservaSalidaDto.setNombre(reservaDto.getNombre());
            reservaSalidaDto.setApellido(reservaDto.getApellido());
            reservaSalidaDto.setCorreoElectronico(reservaDto.getCorreoElectronico());
            reservaSalidaDto.setFechaEntrega(reservaDto.getFechaEntrega());
            reservaSalidaDto.setFechaDevolucion(reservaDto.getFechaDevolucion());
            reservaSalidaDto.setTelefono(reservaDto.getTelefono());
            reservaSalidaDto.setMetodoPago(reservaDto.getMetodoPago());

            return ResponseEntity.ok(reservaSalidaDto);
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ReservaSalidaDto>> obtenerReservasPorUsuario(@PathVariable Long idUsuario) {
        List<ReservaSalidaDto> reservas = reservaService.obtenerReservasPorUsuario(idUsuario);
        return ResponseEntity.ok(reservas);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<ResponseJson> eliminarReserva(@PathVariable Long id) {
        ResponseJson response;
        try {
            reservaService.eliminarReserva(id);
            response = new ResponseJson(true, "Reserva eliminada exitosamente", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response = new ResponseJson(false, "Error al eliminar la reserva: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}