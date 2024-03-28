package com.backend.apirest.autos.alquilerautos.service.impl;

import com.backend.apirest.autos.alquilerautos.dto.entrada.usuario.ReservaEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.entrada.usuario.UsuarioEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.usuario.ReservaSalidaDto;
import com.backend.apirest.autos.alquilerautos.entity.Reserva;
import com.backend.apirest.autos.alquilerautos.entity.Usuario;
import com.backend.apirest.autos.alquilerautos.exceptions.UsuarioNotFoundException;
import com.backend.apirest.autos.alquilerautos.repository.ReservaRepository;
import com.backend.apirest.autos.alquilerautos.repository.UsuarioRepository;
import com.backend.apirest.autos.alquilerautos.repository.VehiculoRepository;
import com.backend.apirest.autos.alquilerautos.service.IReservaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService implements IReservaService {
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private VehiculoRepository vehiculoRepository;

    private ModelMapper modelMapper;

    public ReservaService(ReservaRepository reservaRepository, ModelMapper modelMapper) {
        this.reservaRepository = reservaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long registrarReserva(ReservaEntradaDto reservaDto) {//logica de registrar reserva
        // Convertir ReservaEntradaDto a Reserva
        Reserva reserva = convertirDtoAReserva(reservaDto);

        Reserva reservaRegistrada = reservaRepository.save(reserva);

        // Devolver el ID de la reserva registrada
        return reservaRegistrada.getId();
    }

    public Reserva convertirDtoAReserva(ReservaEntradaDto reservaDto) {

        Reserva reserva= new Reserva();

        reserva.setUsuario(usuarioRepository.findById(reservaDto.getUsuarioId()));
        reserva.setVehiculo(vehiculoRepository.findById(reservaDto.getVehiculoId()));
        reserva.setNombre(reservaDto.getNombre());
        reserva.setApellido(reservaDto.getApellido());
        reserva.setFechaEntrega(reservaDto.getFechaEntrega());
        reserva.setFechaDevolucion(reservaDto.getFechaDevolucion());
        reserva.setCorreoElectronico(reservaDto.getCorreoElectronico());
        reserva.setTelefono(reservaDto.getTelefono());
        reserva.setMetodoPago(reservaDto.getMetodoPago());

        return reserva;
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
