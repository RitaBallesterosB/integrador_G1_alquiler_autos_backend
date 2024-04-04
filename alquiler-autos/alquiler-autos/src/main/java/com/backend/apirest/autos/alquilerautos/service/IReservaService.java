package com.backend.apirest.autos.alquilerautos.service;

import com.backend.apirest.autos.alquilerautos.dto.ReservaConVehiculoDto;
import com.backend.apirest.autos.alquilerautos.dto.entrada.usuario.ReservaEntradaDto;

import java.util.List;

public interface IReservaService {
    public Long registrarReserva(ReservaEntradaDto reservaDto);

    List<ReservaConVehiculoDto> obtenerReservasPorUsuario(Long idUsuario);
}
