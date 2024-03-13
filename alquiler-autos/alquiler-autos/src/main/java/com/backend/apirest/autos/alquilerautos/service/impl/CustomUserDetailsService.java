package com.backend.apirest.autos.alquilerautos.service.impl;

import com.backend.apirest.autos.alquilerautos.entity.Usuario;
import com.backend.apirest.autos.alquilerautos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correoElectronico) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreoElectronico(correoElectronico)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con correo electrónico: " + correoElectronico));

        return org.springframework.security.core.userdetails.User
                .withUsername(correoElectronico)
                .password(usuario.getContrasenia())
                .roles(usuario.isAdministrador() == 1 ? "ADMIN" : "USER")
                .build();
    }
}
