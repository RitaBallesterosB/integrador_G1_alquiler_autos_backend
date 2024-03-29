package com.backend.apirest.autos.alquilerautos.service;



import com.backend.apirest.autos.alquilerautos.dto.entrada.vehiculo.ImagenEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo.ImagenSalidaDto;

import java.util.List;

public interface ImpImagenService {
    ImagenSalidaDto agregarImagen(ImagenEntradaDto imagen);

    List<ImagenSalidaDto> listarGaleriaImagenes(Long vehiculoId);

    List<ImagenSalidaDto> listarGaleriaImagenesVerMas(Long vehiculoId);

    public List<ImagenSalidaDto> listarImagenesPorVehiculo(Long vehiculoId);
}
