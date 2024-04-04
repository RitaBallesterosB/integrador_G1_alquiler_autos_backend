package com.backend.apirest.autos.alquilerautos.service.impl;

import com.backend.apirest.autos.alquilerautos.dto.ReservaConVehiculoDto;
import com.backend.apirest.autos.alquilerautos.dto.entrada.usuario.ReservaEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.entrada.usuario.UsuarioEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.usuario.ReservaSalidaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.usuario.UsuarioSalidaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo.VehiculoSalidaDto;
import com.backend.apirest.autos.alquilerautos.entity.Reserva;
import com.backend.apirest.autos.alquilerautos.entity.Usuario;
import com.backend.apirest.autos.alquilerautos.entity.Vehiculo;
import com.backend.apirest.autos.alquilerautos.exceptions.ReservaNotFoundException;
import com.backend.apirest.autos.alquilerautos.exceptions.UsuarioNotFoundException;
import com.backend.apirest.autos.alquilerautos.repository.ReservaRepository;
import com.backend.apirest.autos.alquilerautos.repository.UsuarioRepository;
import com.backend.apirest.autos.alquilerautos.repository.VehiculoRepository;
import com.backend.apirest.autos.alquilerautos.service.IReservaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<ReservaSalidaDto> listarReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return reservas.stream()
                .map(reserva -> modelMapper.map(reserva, ReservaSalidaDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservaConVehiculoDto> obtenerReservasPorUsuario(Long idUsuario) {
        List<Reserva> reservas = reservaRepository.findByUsuarioIdUsuario(idUsuario);
        return reservas.stream()
                .map(reserva -> {
                    ReservaConVehiculoDto reservaDto = new ReservaConVehiculoDto();
                    reservaDto.setId(reserva.getId());
                    reservaDto.setFechaEntrega(reserva.getFechaEntrega());
                    reservaDto.setFechaDevolucion(reserva.getFechaDevolucion());
                    // Obtener el vehículo correspondiente a esta reserva
                    Optional<Vehiculo> vehiculoOptional = vehiculoRepository.findById(reserva.getVehiculo().getId());
                    vehiculoOptional.ifPresent(vehiculo -> {
                        // Mapear los datos del vehículo al DTO de salida y agregarlos a la reserva DTO
                        VehiculoSalidaDto vehiculoDto = modelMapper.map(vehiculo, VehiculoSalidaDto.class);
                        reservaDto.setVehiculo(vehiculoDto);
                    });

                    // Mapear el usuario de la reserva al DTO de salida y agregarlo a la reserva DTO
                    UsuarioSalidaDto usuarioDto = modelMapper.map(reserva.getUsuario(), UsuarioSalidaDto.class);
                    reservaDto.setUsuario(usuarioDto);

                    // Agrega los campos adicionales a reservaDto si es necesario

                    return reservaDto;
                })
                .collect(Collectors.toList());
    }

    public ReservaSalidaDto obtenerReservaPorId(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ReservaNotFoundException("Reserva no encontrada para el ID: " + id));
        return modelMapper.map(reserva, ReservaSalidaDto.class);
    }

    /*public ReservaSalidaDto actualizarReserva(Long id, ReservaEntradaDto reservaEntradaDto) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ReservaNotFoundException("Reserva no encontrada para el ID: " + id));
        modelMapper.map(reservaEntradaDto, reserva);
        reserva = reservaRepository.save(reserva);
        return modelMapper.map(reserva, ReservaSalidaDto.class);
    }*/

    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }
    public Reserva convertirDtoAReserva(ReservaEntradaDto reservaDto) {
        Reserva reserva = new Reserva();

        reserva.setVehiculo(vehiculoRepository.findById(reservaDto.getVehiculoId()).orElse(null));
        reserva.setUsuario(usuarioRepository.findById(reservaDto.getUsuarioId()).orElse(null));
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
    private void configureMapping() {
        modelMapper.createTypeMap(ReservaEntradaDto.class, Reserva.class)
                .addMappings(mapper -> {
                    mapper.skip(Reserva::setId);
                    mapper.map(src -> usuarioRepository.findById(src.getUsuarioId()).orElse(null), Reserva::setUsuario);
                    mapper.map(src -> vehiculoRepository.findById(src.getVehiculoId()).orElse(null), Reserva::setVehiculo);
                });
        modelMapper.createTypeMap(Reserva.class, ReservaSalidaDto.class)
                .addMapping(Reserva::getVehiculo, ReservaSalidaDto::setVehiculoId)
                .addMapping(Reserva::getUsuario, ReservaSalidaDto::setUsuarioId);
    }



    private Reserva dtoEntradaAEntidad(ReservaEntradaDto reservaEntradaDto) {
        configureMapping(); // Configura el mapeo antes de la conversión
        return modelMapper.map(reservaEntradaDto, Reserva.class);
    }

    private ReservaSalidaDto entidadADtoSalida(Reserva reserva) {
        configureMapping(); // Configura el mapeo antes de la conversión
        return modelMapper.map(reserva, ReservaSalidaDto.class);
    }

    private ReservaSalidaDto convertirReservaASalidaDto(Reserva reserva) {
        return modelMapper.map(reserva, ReservaSalidaDto.class);
    }
}