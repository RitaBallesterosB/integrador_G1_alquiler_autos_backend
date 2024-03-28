package com.backend.apirest.autos.alquilerautos.service;

import com.backend.apirest.autos.alquilerautos.dto.entrada.usuario.ReservaEntradaDto;

public interface IReservaService {
    public Long registrarReserva(ReservaEntradaDto reservaDto);
}
