package com.backend.apirest.autos.alquilerautos.service.impl;

import com.backend.apirest.autos.alquilerautos.dto.entrada.vehiculo.CategoriaEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo.CategoriaSalidaDto;
import com.backend.apirest.autos.alquilerautos.entity.Categoria;
import com.backend.apirest.autos.alquilerautos.entity.Vehiculo;
import com.backend.apirest.autos.alquilerautos.exceptions.BadRequestException;
import com.backend.apirest.autos.alquilerautos.exceptions.ResourceNotFoundException;
import com.backend.apirest.autos.alquilerautos.repository.CategoriaRepository;
import com.backend.apirest.autos.alquilerautos.repository.VehiculoRepository;
import com.backend.apirest.autos.alquilerautos.service.ICategoriaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo.VehiculoSalidaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo.CategoriaSalidaDto;



import java.util.Optional;

@Service
public class CategoriaService implements ICategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    public CategoriaSalidaDto agregarCategoria(CategoriaEntradaDto categoriaDto) throws BadRequestException {
        if (categoriaDto == null) {
            throw new BadRequestException("La categoría no puede ser nula");
        }

        Categoria categoria = new Categoria();
        BeanUtils.copyProperties(categoriaDto, categoria);

        Categoria categoriaGuardada = categoriaRepository.save(categoria);

        CategoriaSalidaDto categoriaSalidaDto = new CategoriaSalidaDto();
        BeanUtils.copyProperties(categoriaGuardada, categoriaSalidaDto);

        return categoriaSalidaDto;
    }

    @Override
    public void asignarCategoriaAVehiculo(Long idVehiculo, Long idCategoria) throws ResourceNotFoundException {
        // Verificar si el vehículo existe
        Optional<Vehiculo> vehiculoOptional = vehiculoRepository.findById(idVehiculo);
        if (!vehiculoOptional.isPresent()) {
            throw new ResourceNotFoundException("Vehículo no encontrado con ID: " + idVehiculo);
        }

        // Obtener el vehículo
        Vehiculo vehiculo = vehiculoOptional.get();

        // Verificar si la categoría existe
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(idCategoria);
        if (!categoriaOptional.isPresent()) {
            throw new ResourceNotFoundException("Categoría no encontrada con ID: " + idCategoria);
        }

        // Obtener la categoría
        Categoria categoria = categoriaOptional.get();

        // Asignar la categoría al vehículo
        vehiculo.setCategoria(categoria);

        // Guardar el vehículo actualizado en la base de datos
        vehiculoRepository.save(vehiculo);
    }

    @Override
    public VehiculoSalidaDto cambiarCategoriaDeVehiculo(Long idVehiculo, Long idCategoria) throws ResourceNotFoundException {
        // Paso 1: Buscar el vehículo por su ID
        Optional<Vehiculo> vehiculoOptional = vehiculoRepository.findById(idVehiculo);
        if (!vehiculoOptional.isPresent()) {
            throw new ResourceNotFoundException("Vehículo no encontrado con ID: " + idVehiculo);
        }

        // Paso 2: Buscar la nueva categoría por su ID
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(idCategoria);
        if (!categoriaOptional.isPresent()) {
            throw new ResourceNotFoundException("Categoría no encontrada con ID: " + idCategoria);
        }

        // Obtener el vehículo y la nueva categoría
        Vehiculo vehiculo = vehiculoOptional.get();
        Categoria nuevaCategoria = categoriaOptional.get();

        // Paso 3: Asignar la nueva categoría al vehículo
        vehiculo.setCategoria(nuevaCategoria);

        // Paso 4: Guardar el vehículo actualizado en la base de datos
        vehiculoRepository.save(vehiculo);

        // Paso 5: Convertir el vehículo actualizado a un DTO de salida
        return convertirAVehiculoSalidaDto(vehiculo);
    }
    // Método para convertir un Vehiculo a un DTO de salida
    private VehiculoSalidaDto convertirAVehiculoSalidaDto(Vehiculo vehiculo) {
        VehiculoSalidaDto vehiculoSalidaDto = new VehiculoSalidaDto();
        vehiculoSalidaDto.setId(vehiculo.getId());

        CategoriaSalidaDto categoriaSalidaDto = new CategoriaSalidaDto();
        categoriaSalidaDto.setId(vehiculo.getCategoria().getId());
        vehiculoSalidaDto.setCategoria(categoriaSalidaDto);
        return vehiculoSalidaDto;
    }


}










