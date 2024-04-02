package com.backend.apirest.autos.alquilerautos.service.impl;

import com.backend.apirest.autos.alquilerautos.dto.entrada.vehiculo.CategoriaEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.entrada.vehiculo.ImagenEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.entrada.vehiculo.VehiculoEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo.CategoriaSalidaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo.ImagenSalidaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.vehiculo.VehiculoSalidaDto;
import com.backend.apirest.autos.alquilerautos.entity.Categoria;
import com.backend.apirest.autos.alquilerautos.entity.Reserva;
import com.backend.apirest.autos.alquilerautos.entity.Vehiculo;
import com.backend.apirest.autos.alquilerautos.entity.Imagen;
import com.backend.apirest.autos.alquilerautos.exceptions.VehiculoException;
import com.backend.apirest.autos.alquilerautos.repository.ImagenRepository;
import com.backend.apirest.autos.alquilerautos.repository.VehiculoRepository;
import com.backend.apirest.autos.alquilerautos.repository.CategoriaRepository;
import com.backend.apirest.autos.alquilerautos.service.IVehiculoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VehiculoService implements IVehiculoService {

    private final Logger LOGGER = LoggerFactory.getLogger(VehiculoService.class);
    private final VehiculoRepository vehiculoRepository;

    private final ImagenRepository imagenRepository;

    private final CategoriaRepository categoriaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public VehiculoService(VehiculoRepository vehiculoRepository, ImagenRepository imagenRepository, CategoriaRepository categoriaRepository, ModelMapper modelMapper) {
        this.vehiculoRepository = vehiculoRepository;
        this.imagenRepository = imagenRepository;
        this.categoriaRepository = categoriaRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public VehiculoSalidaDto agregarVehiculo(VehiculoEntradaDto vehiculoDto) {
        try {
            Vehiculo vehiculo = dtoEntradaAEntidad(vehiculoDto);

            // Obtener la categoría existente de la base de datos por su ID
            Long categoriaId = vehiculoDto.getCategoria().getId();
            Categoria categoria = categoriaRepository.findById(categoriaId)
                    .orElseThrow(() -> new NoSuchElementException("No se encontró una categoría con el ID especificado: " + categoriaId));

            // Asignar la categoría al vehículo
            vehiculo.setCategoria(categoria);

            // Guardar el vehículo en la base de datos
            Vehiculo vehiculoGuardado = vehiculoRepository.save(vehiculo);

            // Mapear el vehículo guardado a un DTO de salida y devolverlo
            VehiculoSalidaDto vehiculoSalidaDto = entidadADtoSalida(vehiculoGuardado);
            LOGGER.info("Vehiculo agregado exitosamente: {}", vehiculoSalidaDto);
            return vehiculoSalidaDto;
        } catch (Exception e) {
            LOGGER.error("Se produjo un error al agregar el vehículo: {}", e.getMessage());
            throw new RuntimeException("Error al agregar el vehículo", e);
        }
    }


    //___________________________________________________________________________
    @Override
    public VehiculoSalidaDto agregarImagenesAVehiculo(Long idVehiculo, List<ImagenEntradaDto> nuevasImagenes) {
        // Buscar el vehículo en la base de datos por su ID
        Optional<Vehiculo> vehiculoOptional = vehiculoRepository.findById(idVehiculo);
        if (vehiculoOptional.isEmpty()) {
            LOGGER.error("Error: Vehiculo no encontrado");
            throw new NoSuchElementException("No se encontró un vehículo con el ID especificado: " + idVehiculo);
        }
        Vehiculo vehiculo = vehiculoOptional.get();

        // Mapear las nuevas imágenes a entidades Imagen y establecer la relación con el vehículo
        List<Imagen> imagenes = nuevasImagenes.stream()
                .map(imagenDto -> {
                    Imagen imagen = modelMapper.map(imagenDto, Imagen.class);
                    imagen.setVehiculo(vehiculo); // Establecer la relación con el vehículo
                    return imagen;
                })
                .collect(Collectors.toList());

        // Agregar las nuevas imágenes a la lista de imágenes del vehículo
        vehiculo.getImagenes().addAll(imagenes);

        // Guardar el vehículo actualizado en la base de datos
        vehiculoRepository.save(vehiculo);

        // Mapear el vehículo actualizado a un DTO de salida y devolverlo
        return modelMapper.map(vehiculo, VehiculoSalidaDto.class);
    }




    //___________________________________________________________________________

    @Override
    public List<VehiculoSalidaDto> obtenerVehiculosAleatorios() {
        List<Vehiculo> vehiculos = vehiculoRepository.findAll();
        Collections.shuffle(vehiculos); // Mezcla aleatoriamente la lista de vehículos

        List<VehiculoSalidaDto> vehiculosAleatorios = new ArrayList<>();

        for (Vehiculo vehiculo : vehiculos) {
            // Seleccionar una sola imagen aleatoria del vehículo
            List<ImagenSalidaDto> imagenesAleatorias = new ArrayList<>();
            if (!vehiculo.getImagenes().isEmpty()) {
                Random random = new Random();
                int indiceAleatorio = random.nextInt(vehiculo.getImagenes().size());
                ImagenSalidaDto imagenAleatoria = modelMapper.map(vehiculo.getImagenes().get(indiceAleatorio), ImagenSalidaDto.class);
                imagenesAleatorias.add(imagenAleatoria);
            }

            // Crear un DTO de salida para el vehículo con solo una imagen
            VehiculoSalidaDto vehiculoSalidaDto = modelMapper.map(vehiculo, VehiculoSalidaDto.class);
            vehiculoSalidaDto.setImagenes(imagenesAleatorias);

            // Agregar el vehículo con una sola imagen a la lista de vehículos aleatorios
            vehiculosAleatorios.add(vehiculoSalidaDto);
        }

        return vehiculosAleatorios;
    }


    //___________________________________________________________________________
    @Override
    public VehiculoSalidaDto obtenerVehiculoPorIdConImagenes(Long id) {
        Optional<Vehiculo> vehiculoOptional = vehiculoRepository.findById(id);
        if (vehiculoOptional.isPresent()) {
            Vehiculo vehiculo = vehiculoOptional.get();
            VehiculoSalidaDto vehiculoSalidaDto = modelMapper.map(vehiculo, VehiculoSalidaDto.class);
            // Mapear las imágenes del vehículo al DTO de salida
            List<ImagenSalidaDto> imagenes = vehiculo.getImagenes().stream()
                    .map(imagen -> modelMapper.map(imagen, ImagenSalidaDto.class))
                    .collect(Collectors.toList());
            vehiculoSalidaDto.setImagenes(imagenes);
            return vehiculoSalidaDto;
        } else {
            throw new VehiculoException("Vehículo no encontrado");
        }
    }

    //___________________________________________________________________________

    @Override
    public List<VehiculoSalidaDto> listarVehiculos() {
        List<Vehiculo> vehiculos = vehiculoRepository.findAll();
        return vehiculos.stream()
                .map(this::entidadADtoSalida)
                .collect(Collectors.toList());
    }
    //___________________________________________________________________________
    @Override
    public void eliminarVehiculo(Long id) {
        // Verificar si el vehículo existe antes de intentar eliminarlo
        Optional<Vehiculo> vehiculoOptional = vehiculoRepository.findById(id);
        if (vehiculoOptional.isEmpty()) {
            throw new NoSuchElementException("No se encontró un vehículo con el ID especificado");
        }
        Vehiculo vehiculo = vehiculoOptional.get();

        // Obtener la lista de imágenes asociadas al vehículo
        List<Imagen> imagenes = vehiculo.getImagenes();
        System.out.println(imagenes);
        // Eliminar cada imagen de la base de datos
        for (Imagen imagen : imagenes) {
            imagenRepository.delete(imagen);
        }
        vehiculoRepository.deleteById(id);
    }

//---------------------------------------------------------------------------------------
@Override
public List<VehiculoSalidaDto> buscarVehiculos(String consulta, List<Long> categoria, String fechaEntrega, String fechaDevolucion) throws ParseException {
    List<VehiculoSalidaDto> vehiculosDTO = new ArrayList<>();
    List<Vehiculo> vehiculos = new ArrayList<>();

    if (consulta == null || consulta.isEmpty()) {
        // Si no hay consulta, pero hay al menos una categoría especificada
        if (categoria != null && !categoria.isEmpty()) {
            vehiculos = vehiculoRepository.findByCategoria_IdIn(categoria);
        } else {
            vehiculos = vehiculoRepository.findAll();
        }

        // Filtrar vehículos reservados para las fechas dadas, si se proporcionan
        if (fechaEntrega != null && !fechaEntrega.isEmpty() && fechaDevolucion != null && !fechaDevolucion.isEmpty()) {
            vehiculos = filtrarVehiculosReservados(vehiculos, fechaEntrega, fechaDevolucion);
        }
    } else {
        // Si hay una consulta, pero no hay categoría especificada
        if (categoria == null || categoria.isEmpty()) {
            vehiculos = vehiculoRepository.findByNombreContainingIgnoreCase(consulta);
        } else {
            vehiculos = vehiculoRepository.findByNombreContainingIgnoreCaseAndCategoria_IdIn(consulta, categoria);
        }

        // Filtrar vehículos reservados para las fechas dadas, si se proporcionan
        if (fechaEntrega != null && !fechaEntrega.isEmpty() && fechaDevolucion != null && !fechaDevolucion.isEmpty()) {
            vehiculos = filtrarVehiculosReservados(vehiculos, fechaEntrega, fechaDevolucion);
        }
    }

    // Convertir los vehículos obtenidos a vehículosDTO
    for (Vehiculo vehiculo : vehiculos) {
        VehiculoSalidaDto vehiculoDTO = new VehiculoSalidaDto();
        vehiculoDTO.setId(vehiculo.getId());
        vehiculoDTO.setNombre(vehiculo.getNombre());
        vehiculoDTO.setDescripcion(vehiculo.getDescripcion());

        // Convertir la lista de entidades Imagen a una lista de DTO ImagenSalidaDto
        List<ImagenSalidaDto> imagenesDTO = new ArrayList<>();
        for (Imagen imagen : vehiculo.getImagenes()) {
            ImagenSalidaDto imagenDTO = new ImagenSalidaDto();
            imagenDTO.setNombre(imagen.getNombre()); // Asignar el nombre de la imagen
            imagenDTO.setUrl(imagen.getUrl()); // Asignar la URL de la imagen
            // Copiar otras propiedades de la entidad Imagen a la entidad DTO ImagenSalidaDto si es necesario
            // imagenDTO.setPropiedad(imagen.getPropiedad());
            imagenesDTO.add(imagenDTO);
        }
        vehiculoDTO.setImagenes(imagenesDTO);

        // Convertir la entidad Categoria a DTO CategoriaSalidaDto
        CategoriaSalidaDto categoriaDTO = new CategoriaSalidaDto();
        categoriaDTO.setId(vehiculo.getCategoria().getId());
        // Copiar otras propiedades de la entidad Categoria a la entidad DTO CategoriaSalidaDto si es necesario
        // categoriaDTO.setPropiedad(vehiculo.getCategoria().getPropiedad());
        vehiculoDTO.setCategoria(categoriaDTO);

        vehiculosDTO.add(vehiculoDTO);
    }

    return vehiculosDTO;
}

    private List<Vehiculo> filtrarVehiculosReservados(List<Vehiculo> vehiculos, String fechaEntrega, String fechaDevolucion) throws ParseException {
        List<Vehiculo> vehiculosFiltrados = new ArrayList<>();

        // Convertir las fechas de tipo String a tipo Date (asumiendo que están en un formato adecuado)
        Date fechaInicio = convertirStringADate(fechaEntrega);
        Date fechaFin = convertirStringADate(fechaDevolucion);

        // Si no hay vehículos, devolver una lista vacía
        if (vehiculos.isEmpty()) {
            return vehiculosFiltrados;
        }

        for (Vehiculo vehiculo : vehiculos) {
            // Si el vehículo no tiene reservas, agregarlo directamente a la lista de vehículos filtrados
            if (vehiculo.getReservas().isEmpty()) {
                System.out.println("filtado");
                vehiculosFiltrados.add(vehiculo);
            }else{
                boolean disponible = true;
                System.out.println(vehiculo.getReservas());
                // Verificar si hay alguna superposición entre las fechas de reserva y las fechas dadas
                for (Reserva reserva : vehiculo.getReservas()) {

                    Date fechaReservaInicio = reserva.getFechaEntrega();
                    Date fechaReservaFin = reserva.getFechaDevolucion();

                    // Verificar si hay superposición entre los intervalos de tiempo de la reserva y las fechas dadas
                    if (!(fechaReservaInicio.after(fechaFin) || fechaReservaFin.before(fechaInicio))) {
                        System.out.println("entro");
                        disponible = false;
                        break; // No es necesario seguir buscando más reservas para este vehículo
                    }
                }

                // Si el vehículo está disponible para las fechas dadas, agrégalo a la lista de vehículos filtrados
                if (disponible) {
                    vehiculosFiltrados.add(vehiculo);
                }
            }

        }
        System.out.println(vehiculosFiltrados);
        return vehiculosFiltrados;
    }



    private Date convertirStringADate(String fechaStr) throws ParseException {
        // Define el formato de fecha esperado
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        // Intenta parsear el String a Date
        Date fecha = formatter.parse(fechaStr);

        return fecha;
    }

    // Método para obtener sugerencias basadas en la consulta ingresada
    @Override
    public List<String> obtenerSugerencias(String consulta) {
        // Buscar vehículos cuyos nombres contengan la consulta
        List<Vehiculo> vehiculos = vehiculoRepository.findByNombreContainingIgnoreCase(consulta);
        // Extraer los nombres de los vehículos y devolverlos como sugerencias
        return vehiculos.stream()
                .map(Vehiculo::getNombre)
                .collect(Collectors.toList());
    }

    public List<Date> listarFechasOcupadas(Long id) {
        List<Date> fechasOcupadas = new ArrayList<>();//lista de fechas
        List<Reserva> reservas = vehiculoRepository.findById(id).get().getReservas();
        Calendar calendar = Calendar.getInstance();
        Date fechaEntrega = null;
        Date fechaDevolucion = null;
        for (Reserva reserva: reservas) {
            fechaEntrega= reserva.getFechaEntrega();
            calendar.setTime(fechaEntrega);
            System.out.println(fechaEntrega);//probando si funciona
            fechasOcupadas.add(fechaEntrega);
            fechaDevolucion=reserva.getFechaDevolucion();
            System.out.println(fechaDevolucion);
            while(!fechaEntrega.equals(fechaDevolucion)){
                // Sumar un día
                calendar.add(Calendar.DAY_OF_YEAR, 1);
                // Obtener la fecha resultante
                fechaEntrega = calendar.getTime();
                System.out.println(fechaEntrega);
                fechasOcupadas.add(fechaEntrega);
            }
        }

        return fechasOcupadas;
    }
    private void configureMapping() {
        if (modelMapper.getTypeMap(CategoriaEntradaDto.class, Categoria.class) == null) {
            modelMapper.createTypeMap(CategoriaEntradaDto.class, Categoria.class)
                    .addMappings(mapper -> mapper.skip(Categoria::setId));
        }
        if (modelMapper.getTypeMap(Categoria.class, CategoriaSalidaDto.class) == null) {
            modelMapper.createTypeMap(Categoria.class, CategoriaSalidaDto.class);
        }
        if (modelMapper.getTypeMap(Vehiculo.class, VehiculoSalidaDto.class) == null) {
            modelMapper.createTypeMap(Vehiculo.class, VehiculoSalidaDto.class)
                    .addMappings(mapper -> mapper.map(Vehiculo::getImagenes, VehiculoSalidaDto::setImagenes));
        }
    }



    private Vehiculo dtoEntradaAEntidad(VehiculoEntradaDto vehiculoDto) {
        Vehiculo vehiculo = modelMapper.map(vehiculoDto, Vehiculo.class);

        // Obtener la categoría existente de la base de datos por su ID
        Long categoriaId = vehiculoDto.getCategoria().getId();
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new NoSuchElementException("No se encontró una categoría con el ID especificado: " + categoriaId));

        vehiculo.setCategoria(categoria);

        if (vehiculoDto.getImagenes() != null) {
            List<Imagen> imagenes = vehiculoDto.getImagenes().stream()
                    .map(imagenDto -> modelMapper.map(imagenDto, Imagen.class))
                    .collect(Collectors.toList());
            vehiculo.setImagenes(imagenes);
        }
        return vehiculo;
    }

    private VehiculoSalidaDto entidadADtoSalida(Vehiculo vehiculo) {
        VehiculoSalidaDto vehiculoSalidaDto = modelMapper.map(vehiculo, VehiculoSalidaDto.class);

        if (vehiculo.getCategoria() != null) {
            CategoriaSalidaDto categoriaSalidaDto = modelMapper.map(vehiculo.getCategoria(), CategoriaSalidaDto.class);
            vehiculoSalidaDto.setCategoria(categoriaSalidaDto);
        }
        return vehiculoSalidaDto;
    }

}