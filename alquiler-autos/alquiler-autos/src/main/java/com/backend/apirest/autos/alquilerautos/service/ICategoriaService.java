package com.backend.apirest.autos.alquilerautos.service;

import com.backend.apirest.autos.alquilerautos.dto.entrada.vehiculo.CategoriaEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo.CategoriaSalidaDto;

public interface ICategoriaService {
    CategoriaSalidaDto agregarCategoria(CategoriaEntradaDto vehiculo);
}
