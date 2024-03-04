package com.backend.apirest.autos.alquilerautos.service.impl;

import com.backend.apirest.autos.alquilerautos.dto.entrada.vehiculo.CategoriaEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo.CategoriaSalidaDto;
import com.backend.apirest.autos.alquilerautos.entity.Categoria;
import com.backend.apirest.autos.alquilerautos.repository.CategoriaRepository;
import com.backend.apirest.autos.alquilerautos.service.ICategoriaService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService implements ICategoriaService {
    private final Logger LOGGER = LoggerFactory.getLogger(CategoriaService.class);
    private final CategoriaRepository categoriaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository, ModelMapper modelMapper) {
        this.categoriaRepository = categoriaRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    @Transactional
    public CategoriaSalidaDto agregarCategoria(CategoriaEntradaDto categoriaDto) {
        Categoria categoria = modelMapper.map(categoriaDto, Categoria.class);
        Categoria categoriaAgregada = categoriaRepository.save(categoria);
        return modelMapper.map(categoriaAgregada, CategoriaSalidaDto.class);
    }

    private void configureMapping() {
        modelMapper.typeMap(CategoriaEntradaDto.class, Categoria.class);
        modelMapper.typeMap(Categoria.class, CategoriaSalidaDto.class);
    }

    private Categoria dtoEntradaAEntidad(CategoriaEntradaDto categoriaEntradaDto) {
        return modelMapper.map(categoriaEntradaDto, Categoria.class);
    }

    private CategoriaSalidaDto entidadAdtoSalida(Categoria categoria) {
        return modelMapper.map(categoria, CategoriaSalidaDto.class);
    }

}


