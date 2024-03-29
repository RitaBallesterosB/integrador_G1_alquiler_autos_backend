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

    private void configureMapping() {
        modelMapper.createTypeMap(ReservaEntradaDto.class, Reserva.class)
                .addMappings(mapper -> {
                    mapper.skip(Reserva::setId);
                    mapper.map(ReservaEntradaDto::getVehiculoId, (dest, value) -> dest.getVehiculo().setId((Long) value));
                    mapper.map(ReservaEntradaDto::getUsuarioId, (dest, value) -> dest.getUsuario().setIdUsuario((Long) value));
                });
        modelMapper.createTypeMap(Reserva.class, ReservaSalidaDto.class)
                .addMapping(src -> src.getVehiculo().getId(), ReservaSalidaDto::setVehiculoId)
                .addMapping(src -> src.getUsuario().getIdUsuario(), ReservaSalidaDto::setUsuarioId);
    }


    private Reserva dtoEntradaAEntidad(ReservaEntradaDto reservaEntradaDto) {
        configureMapping(); // Configura el mapeo antes de la conversión
        return modelMapper.map(reservaEntradaDto, Reserva.class);
    }

    private ReservaSalidaDto entidadADtoSalida(Reserva reserva) {
        configureMapping(); // Configura el mapeo antes de la conversión
        return modelMapper.map(reserva, ReservaSalidaDto.class);
    }


}
