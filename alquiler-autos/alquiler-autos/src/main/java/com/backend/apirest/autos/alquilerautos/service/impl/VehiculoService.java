package com.backend.apirest.autos.alquilerautos.service.impl;

import com.backend.apirest.autos.alquilerautos.dto.entrada.vehiculo.ImagenEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.entrada.vehiculo.VehiculoEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo.VehiculoSalidaDto;
import com.backend.apirest.autos.alquilerautos.entity.Vehiculo;
import com.backend.apirest.autos.alquilerautos.entity.Imagen;
import com.backend.apirest.autos.alquilerautos.repository.VehiculoRepository;
import com.backend.apirest.autos.alquilerautos.service.IVehiculoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class VehiculoService implements IVehiculoService {

    private final Logger LOGGER = LoggerFactory.getLogger(VehiculoService.class);
    private final VehiculoRepository vehiculoRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public VehiculoService(VehiculoRepository vehiculoRepository, ModelMapper modelMapper) {
        this.vehiculoRepository = vehiculoRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public VehiculoSalidaDto agregarVehiculo(VehiculoEntradaDto vehiculoDto) {
        Vehiculo vehiculo = dtoEntradaAEntidad(vehiculoDto);
        Vehiculo vehiculoAgregado = vehiculoRepository.save(vehiculo);
        VehiculoSalidaDto vehiculoSalidaDto = entidadADtoSalida(vehiculoAgregado);
        LOGGER.info("Vehiculo agregado: {}", vehiculoSalidaDto);
        return vehiculoSalidaDto;
    }

    //___________________________________________________________________________
    @Override
    public VehiculoSalidaDto agregarImagenesAVehiculo(Long idVehiculo, List<ImagenEntradaDto> nuevasImagenes) {
        Vehiculo vehiculo = vehiculoRepository.findById(idVehiculo).orElse(null);

        if (vehiculo == null) {
            LOGGER.error("Error: Vehiculo no encontrado");
            return null;
        }

        List<Imagen> imagenes = nuevasImagenes.stream()
                .map(imagenDto -> modelMapper.map(imagenDto, Imagen.class))
                .collect(Collectors.toList());

        vehiculo.getImagenes().addAll(imagenes);
        vehiculoRepository.save(vehiculo);

        return modelMapper.map(vehiculo, VehiculoSalidaDto.class);
    }


    //___________________________________________________________________________
    @Override
    public List<VehiculoSalidaDto> obtenerVehiculosAleatorios() {
        List<Vehiculo> vehiculos = vehiculoRepository.findAll();
        List<Vehiculo> vehiculosAleatorios = new ArrayList<>();
        Random random = new Random();

        // Obtener índices aleatorios sin duplicados
        Set<Integer> indicesAleatorios = new HashSet<>();
        while (indicesAleatorios.size() < Math.min(4, vehiculos.size())) {
            indicesAleatorios.add(random.nextInt(vehiculos.size()));
        }

        // Obtener vehículos correspondientes a los índices aleatorios
        for (Integer indice : indicesAleatorios) {
            vehiculosAleatorios.add(vehiculos.get(indice));
        }

        return vehiculosAleatorios.stream()
                .map(vehiculo -> modelMapper.map(vehiculo, VehiculoSalidaDto.class))
                .collect(Collectors.toList());
    }






    private void configureMapping() {
        modelMapper.typeMap(VehiculoEntradaDto.class, Vehiculo.class)
                .addMappings(mapper -> mapper.map(VehiculoEntradaDto::getImagenes, Vehiculo::setImagenes));

        modelMapper.typeMap(Vehiculo.class, VehiculoSalidaDto.class)
                .addMappings(mapper -> mapper.map(Vehiculo::getImagenes, VehiculoSalidaDto::setImagenes));
    }

    private Vehiculo dtoEntradaAEntidad(VehiculoEntradaDto vehiculoDto) {
        Vehiculo vehiculo = modelMapper.map(vehiculoDto, Vehiculo.class);
        if (vehiculoDto.getImagenes() != null) {
            List<Imagen> imagenes = vehiculoDto.getImagenes().stream()
                    .map(imagenDto -> modelMapper.map(imagenDto, Imagen.class))
                    .collect(Collectors.toList());
            vehiculo.setImagenes(imagenes);
        }
        return vehiculo;
    }

    private VehiculoSalidaDto entidadADtoSalida(Vehiculo vehiculo) {
        return modelMapper.map(vehiculo, VehiculoSalidaDto.class);
    }
}


