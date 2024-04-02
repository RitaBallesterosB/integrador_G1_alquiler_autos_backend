package com.backend.apirest.autos.alquilerautos.service;

import com.backend.apirest.autos.alquilerautos.dto.entrada.vehiculo.ImagenEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.entrada.vehiculo.VehiculoEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.usuario.ReservaFechasSalidaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo.VehiculoSalidaDto;
import com.backend.apirest.autos.alquilerautos.exceptions.BadRequestException;


import java.text.ParseException;
import java.util.List;

public interface IVehiculoService {
    VehiculoSalidaDto agregarVehiculo(VehiculoEntradaDto vehiculo) throws BadRequestException;

    //___________________________________________________________________________________________

    VehiculoSalidaDto agregarImagenesAVehiculo(Long idVehiculo, List<ImagenEntradaDto> nuevasImagenes);

    //___________________________________________________________________________________________

    List<VehiculoSalidaDto> obtenerVehiculosAleatorios();
    //___________________________________________________________________________________________

    VehiculoSalidaDto obtenerVehiculoPorIdConImagenes(Long id);

    //___________________________________________________________________________________________

    List<VehiculoSalidaDto> listarVehiculos();
    //___________________________________________________________________________________________

    void eliminarVehiculo(Long id);

    List<VehiculoSalidaDto> buscarVehiculos(String consulta, List<Long> categoria, String fechaEntrega, String fechaDevolucion) throws ParseException;

    List<String> obtenerSugerencias(String consulta);

    List<ReservaFechasSalidaDto> listarFechasOcupadas(Long id);
}


