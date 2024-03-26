package com.backend.apirest.autos.alquilerautos.service.impl;

import com.backend.apirest.autos.alquilerautos.dto.entrada.usuario.ReservaEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.usuario.ReservaSalidaDto;
import com.backend.apirest.autos.alquilerautos.entity.Reserva;
import com.backend.apirest.autos.alquilerautos.repository.ReservaRepository;
import com.backend.apirest.autos.alquilerautos.service.IReservaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ReservaService implements IReservaService {

    private ReservaRepository reservaRepository;

    private ModelMapper modelMapper;

    public ReservaService(ReservaRepository reservaRepository, ModelMapper modelMapper) {
        this.reservaRepository = reservaRepository;
        this.modelMapper = modelMapper;
    }

    // Configura el mapeo entre DTOs y entidades
   /* private void configureMapping() {
        modelMapper.createTypeMap(ReservaEntradaDto.class, Reserva.class)
                .addMappings(mapper -> mapper.skip(Reserva::setId));
        modelMapper.createTypeMap(Reserva.class, ReservaSalidaDto.class)
                .addMapping(Reserva::getVehiculoId, ReservaSalidaDto::setVehiculoId)
                .addMapping(Reserva::getUsuarioId, ReservaSalidaDto::setUsuarioId);
    }

    // Convierte un DTO de entrada en una entidad
    private Reserva dtoEntradaAEntidad(ReservaInputDTO reservaInputDTO) {
        configureMapping(); // Configura el mapeo antes de la conversión
        return modelMapper.map(reservaInputDTO, Reserva.class);
    }

    // Convierte una entidad en un DTO de salida
    private ReservaOutputDTO entidadADtoSalida(Reserva reserva) {
        configureMapping(); // Configura el mapeo antes de la conversión
        return modelMapper.map(reserva, ReservaOutputDTO.class);
    }
*/
}
