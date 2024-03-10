package com.backend.apirest.autos.alquilerautos.config;

import com.backend.apirest.autos.alquilerautos.repository.UsuarioRepository;
import com.backend.apirest.autos.alquilerautos.service.impl.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDetailsServiceConfig {

    @Bean
    public CustomUserDetailsService userDetailsService(UsuarioRepository usuarioRepository) {
        return new CustomUserDetailsService(usuarioRepository);
    }
}
