package com.backend.apirest.autos.alquilerautos.service.impl;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.backend.apirest.autos.alquilerautos.dto.entrada.usuario.UsuarioEntradaDto;
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
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {

    public static final long EXPIRATION_TIME = 86400000; // 10 días en milisegundos
    public static final String SECRET_KEY = "mySecretKey1234ghgolkgfglkktrk657klo68965thgnvrtky6564354668i6lnulmyjhgfmfkuytgmlfdglt7r66764eiltteklrjl34545i6545rengflksdvgeftiu";
    @Autowired
    private UsuarioRepository usuarioRepository;

    private final ModelMapper modelMapper;

    public UsuarioService(ModelMapper modelMapper,UsuarioRepository usuarioRepository) {
        this.modelMapper = modelMapper;
        this.usuarioRepository = usuarioRepository;
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
    public List<UsuarioSalidaDto> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(this::entidadADtoSalida)
                .collect(Collectors.toList());
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
    private UsuarioSalidaDto entidadADtoSalida(Usuario usuario) {
        UsuarioSalidaDto usuarioSalidaDto = new UsuarioSalidaDto();
        usuarioSalidaDto.setIdUsuario(usuario.getIdUsuario());
        usuarioSalidaDto.setNombre(usuario.getNombre());
        usuarioSalidaDto.setApellido(usuario.getApellido());
        usuarioSalidaDto.setCorreoElectronico(usuario.getCorreoElectronico());
        usuarioSalidaDto.setAdministrador(usuario.isAdministrador());

        return usuarioSalidaDto;
    }
    }