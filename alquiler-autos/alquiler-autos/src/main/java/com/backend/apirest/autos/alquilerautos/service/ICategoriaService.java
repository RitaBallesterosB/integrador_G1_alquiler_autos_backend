package com.backend.apirest.autos.alquilerautos.service;

import com.backend.apirest.autos.alquilerautos.dto.entrada.vehiculo.CategoriaEntradaDto;

import com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo.CategoriaSalidaDto;
import com.backend.apirest.autos.alquilerautos.entity.Categoria;
import com.backend.apirest.autos.alquilerautos.exceptions.BadRequestException;
import com.backend.apirest.autos.alquilerautos.exceptions.ResourceNotFoundException;
import com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo.VehiculoSalidaDto;


import java.util.Optional;

public interface ICategoriaService {
    CategoriaSalidaDto agregarCategoria(CategoriaEntradaDto categoria) throws BadRequestException;
    void asignarCategoriaAVehiculo(Long idVehiculo, Long idCategoria) throws ResourceNotFoundException;
    VehiculoSalidaDto cambiarCategoriaDeVehiculo(Long idVehiculo, Long idCategoria) throws ResourceNotFoundException;

}
