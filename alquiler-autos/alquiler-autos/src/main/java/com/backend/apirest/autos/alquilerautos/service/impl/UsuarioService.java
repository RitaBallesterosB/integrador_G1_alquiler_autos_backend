package com.backend.apirest.autos.alquilerautos.service.impl;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.backend.apirest.autos.alquilerautos.dto.entrada.usuario.UsuarioEntradaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.usuario.GestionUsuarioSalidaDto;
import com.backend.apirest.autos.alquilerautos.dto.salida.usuario.UsuarioSalidaDto;

import com.backend.apirest.autos.alquilerautos.entity.Usuario;
import com.backend.apirest.autos.alquilerautos.exceptions.InvalidCredentialsException;
import com.backend.apirest.autos.alquilerautos.exceptions.UsuarioNotFoundException;
import com.backend.apirest.autos.alquilerautos.repository.UsuarioRepository;
import com.backend.apirest.autos.alquilerautos.service.IUsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService implements IUsuarioService {
    public static final int MAX_LONGITUD_CONTRASENIA = 45;
    public static final long EXPIRATION_TIME = 86400000; // 10 días en milisegundos
    public static final String SECRET_KEY = "mySecretKey1234ghgolkgfglkktrk657klo68965thgnvrtky6564354668i6lnulmyjhgfmfkuytgmlfdglt7r66764eiltteklrjl34545i6545rengflksdvgeftiu";
    @Autowired
    private UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    // Método para autenticar al usuario y generar el JWT
    public String autenticarUsuario(String correoElectronico, String contrasenia) {
        // Buscar el usuario por correo electrónico en la base de datos
        Optional<Usuario> usuarioOptional = usuarioRepository.findByCorreoElectronico(correoElectronico);
        if (!usuarioOptional.isPresent()) {
            throw new UsuarioNotFoundException("Usuario no encontrado");
        }

        // Verificar si la contraseña coincide
        Usuario usuario = usuarioOptional.get();
        if (!usuario.getContrasenia().equals(contrasenia)) {
            throw new InvalidCredentialsException("Credenciales inválidas");
        }

        // Generar el JWT
        String jwt = generateJWT(usuario.getIdUsuario(), usuario.getCorreoElectronico(), usuario.isAdministrador() == 1);

        return jwt;
    }



    // Método para generar el JWT
    private String generateJWT(Long userId, String correoElectronico, boolean isAdmin) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(Long.toString(userId))
                .claim("email", correoElectronico)
                .claim("isAdmin", isAdmin)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }


    @Override
    public Long registrarUsuario(UsuarioEntradaDto usuarioDto) {
        // Verificar si el usuario ya está registrado
        if (usuarioRepository.findByCorreoElectronico(usuarioDto.getCorreoElectronico()).isPresent()) {
            throw new UsuarioNotFoundException("El usuario ya está registrado");
        }


        // Convertir UsuarioEntradaDto a Usuario
        Usuario usuario = convertirDtoAUsuario(usuarioDto);

        // Establecer el usuario como no administrador (0 por defecto)
        usuario.setAdministrador(0);

        // Codificar la contraseña antes de guardarla en la base de datos
        String contraseniaCodificada = passwordEncoder.encode(usuarioDto.getContrasenia());

        // Verificar que la longitud de la contraseña codificada no exceda el límite de la base de datos
        if (contraseniaCodificada.length() > 45) {
            throw new IllegalArgumentException("La contraseña codificada excede la longitud máxima permitida en la base de datos");
        }

        usuario.setContrasenia(contraseniaCodificada);

        Usuario usuarioRegistrado = usuarioRepository.save(usuario);

        // Devolver el ID del usuario registrado
        return usuarioRegistrado.getIdUsuario();
    }



    @Override
    public void administrarUsuario(Long idUsuario, boolean isAdmin) {
        // Verificar si el usuario existe
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        if (!usuarioOptional.isPresent()) {
            throw new UsuarioNotFoundException("Usuario no encontrado");
        }

        // Cambiar el estado de administrador del usuario
        Usuario usuario = usuarioOptional.get();
        usuario.setAdministrador(isAdmin ? 1 : 0);
        usuarioRepository.save(usuario);
    }

    // Conversión Dtos
    private Usuario convertirDtoAUsuario(UsuarioEntradaDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setApellido(usuarioDto.getApellido());
        usuario.setCorreoElectronico(usuarioDto.getCorreoElectronico());
        return usuario;
    }

        @Override
        public List<GestionUsuarioSalidaDto> listarUsuarios() {
            List<Usuario> usuarios = usuarioRepository.findAll();
            return usuarios.stream()
                    .map(this::entidadADtoSalida)
                    .collect(Collectors.toList());
        }
        // actualiza el perfil del usuario a administrador o cliente
        public GestionUsuarioSalidaDto actualizarPermisosUsuario(Long idUsuario,Integer administrador){

            // Buscar el usuario en la base de datos por su ID
            Usuario usuario = usuarioRepository.findById(idUsuario)
                    .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado con ID: " + idUsuario));

            // Actualizar el campo administrador
            usuario.setAdministrador(administrador);

            // Guardar los cambios en la base de datos
            usuarioRepository.save(usuario);

            // Crear y devolver un objeto de salida con los datos actualizados del usuario
            return entidadADtoSalida(usuario);

        }

    @Override
    public List<GestionUsuarioSalidaDto> obtenerUsuarioPorEmail(String correoElectronico) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByCorreoElectronico(correoElectronico);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            GestionUsuarioSalidaDto usuarioSalidaDto = entidadADtoSalida(usuario);
            return Collections.singletonList(usuarioSalidaDto);
        } else {
            // Manejar el caso en que el usuario no sea encontrado
            throw new UsuarioNotFoundException("Usuario no encontrado para el correo electrónico: " + correoElectronico);
        }
    }

    // Método para configurar los mapeos
        private void configureMapping() {
            if (modelMapper.getTypeMap(UsuarioEntradaDto.class, Usuario.class) == null) {
                modelMapper.createTypeMap(UsuarioEntradaDto.class, Usuario.class);
            }
            if (modelMapper.getTypeMap(Usuario.class, UsuarioSalidaDto.class) == null) {
                modelMapper.createTypeMap(Usuario.class, UsuarioSalidaDto.class);
            }
        }

    // Método para convertir DTO de entrada a entidad Usuario
    private Usuario dtoEntradaAEntidad(UsuarioEntradaDto usuarioDto) {
        return modelMapper.map(usuarioDto, Usuario.class);
    }

    // Método privado para mapear una entidad Usuario a un DTO de salida
    private GestionUsuarioSalidaDto entidadADtoSalida(Usuario usuario) {
        GestionUsuarioSalidaDto gestionusuarioSalidaDto = new GestionUsuarioSalidaDto();
        gestionusuarioSalidaDto.setIdUsuario(usuario.getIdUsuario());
        gestionusuarioSalidaDto.setNombre(usuario.getNombre());
        gestionusuarioSalidaDto.setApellido(usuario.getApellido());
        gestionusuarioSalidaDto.setCorreoElectronico(usuario.getCorreoElectronico());
        gestionusuarioSalidaDto.setAdministrador(usuario.isAdministrador());

        return gestionusuarioSalidaDto;
    }


}


