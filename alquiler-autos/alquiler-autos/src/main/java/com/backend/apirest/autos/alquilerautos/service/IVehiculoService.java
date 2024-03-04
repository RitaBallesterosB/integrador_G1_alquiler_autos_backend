package com.backend.apirest.autos.alquilerautos.service;

import com.backend.apirest.autos.alquilerautos.dto.entrada.vehiculo.ImagenEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.entrada.vehiculo.VehiculoEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo.VehiculoSalidaDto;


import java.util.List;

public interface IVehiculoService {
    VehiculoSalidaDto agregarVehiculo(VehiculoEntradaDto vehiculo);

    //___________________________________________________________________________________________

    VehiculoSalidaDto agregarImagenesAVehiculo(Long idVehiculo, List<ImagenEntradaDto> nuevasImagenes);

    //___________________________________________________________________________________________

    List<VehiculoSalidaDto> obtenerVehiculosAleatorios();
}


