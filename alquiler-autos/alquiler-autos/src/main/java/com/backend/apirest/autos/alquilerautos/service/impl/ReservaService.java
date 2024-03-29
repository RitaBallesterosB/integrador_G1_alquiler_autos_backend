package com.backend.apirest.autos.alquilerautos.service.impl;

import com.backend.apirest.autos.alquilerautos.dto.entrada.usuario.ReservaEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.entrada.usuario.UsuarioEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.usuario.ReservaSalidaDto;
import com.backend.apirest.autos.alquilerautos.entity.Reserva;
import com.backend.apirest.autos.alquilerautos.entity.Usuario;
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

    public List<ReservaSalidaDto> obtenerReservasPorUsuario(Long idUsuario) {
        List<Reserva> reservas = reservaRepository.findByUsuarioIdUsuario(idUsuario);
        return reservas.stream()
                .map(this::convertirReservaASalidaDto)
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