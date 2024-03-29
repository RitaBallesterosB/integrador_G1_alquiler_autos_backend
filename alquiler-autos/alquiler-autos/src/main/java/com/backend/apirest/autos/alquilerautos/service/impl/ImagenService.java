package com.backend.apirest.autos.alquilerautos.service.impl;

import com.backend.apirest.autos.alquilerautos.dto.entrada.vehiculo.ImagenEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo.ImagenSalidaDto;
import com.backend.apirest.autos.alquilerautos.entity.Imagen;
import com.backend.apirest.autos.alquilerautos.repository.ImagenRepository;
import com.backend.apirest.autos.alquilerautos.service.ImpImagenService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImagenService  implements ImpImagenService {
    private final Logger LOGGER= LoggerFactory.getLogger(ImagenService.class);
    private final ImagenRepository imagenRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public ImagenService(ImagenRepository imagenRepository, ModelMapper modelMapper) {
        this.imagenRepository = imagenRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public ImagenSalidaDto agregarImagen(ImagenEntradaDto imagen) {
        Imagen imagenAgregada = imagenRepository.save(dtoEntradaAEntidad(imagen));
        ImagenSalidaDto imagenSalidaDto = entidadADtoSalida(imagenAgregada);
        LOGGER.info ("Imagen agregada: {}", imagenSalidaDto);

        return  imagenSalidaDto;
    }

    @Override
    public List<ImagenSalidaDto> listarGaleriaImagenes(Long vehiculoId) {
        // Buscar imágenes relacionadas con el vehículoId dado
        List<Imagen> imagenes = imagenRepository.findByVehiculoId(vehiculoId);

        // Limitar la cantidad de imágenes a un máximo de 5
        int numImagenes = Math.min(imagenes.size(), 5);

        // Obtener las primeras 5 imágenes o menos si hay menos disponibles
        List<Imagen> imagenesLimitadas = imagenes.stream()
                .limit(numImagenes)
                .collect(Collectors.toList());

        // Convierte las imágenes en objetos ImagenSalidaDto
        return imagenesLimitadas.stream()
                .map(this::entidadADtoSalida)
                .collect(Collectors.toList());
    }

    @Override
    public List<ImagenSalidaDto> listarGaleriaImagenesVerMas(Long vehiculoId) {
        // Buscar imágenes relacionadas con el vehículoId dado
        List<Imagen> imagenes = imagenRepository.findByVehiculoId(vehiculoId);

        // Limitar la cantidad de imágenes a un máximo de 10
        int numImagenes = Math.min(imagenes.size(), 10);

        // Obtener las primeras 10 imágenes o menos si hay menos disponibles
        List<Imagen> imagenesLimitadas = imagenes.stream()
                .limit(numImagenes)
                .collect(Collectors.toList());

        // Convierte las imágenes en objetos ImagenSalidaDto
        return imagenesLimitadas.stream()
                .map(this::entidadADtoSalida)
                .collect(Collectors.toList());
    }

    @Override
    public List<ImagenSalidaDto> listarImagenesPorVehiculo(Long vehiculoId) {
        // Obtener las imágenes asociadas al vehículo con el ID proporcionado
        List<Imagen> imagenes = imagenRepository.findByVehiculoId(vehiculoId);

        // Mapear las imágenes a objetos ImagenSalidaDto
        return imagenes.stream()
                .map(this::entidadADtoSalida)
                .collect(Collectors.toList());
    }


    private void configureMapping() {
        modelMapper.typeMap(ImagenEntradaDto.class, Imagen.class);
        modelMapper.typeMap(Imagen.class, ImagenSalidaDto.class)
                .addMappings(mapper -> mapper.map(Imagen::getVehiculo, ImagenSalidaDto::setVehiculo));

    }

    private Imagen dtoEntradaAEntidad(ImagenEntradaDto imagenEntradaDto) {
        return  modelMapper.map(imagenEntradaDto, Imagen.class);
    }

    private ImagenSalidaDto   entidadADtoSalida (Imagen imagen) {
        return  modelMapper.map(imagen, ImagenSalidaDto.class);
    }
}
