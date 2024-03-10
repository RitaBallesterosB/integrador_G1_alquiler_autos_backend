package com.backend.apirest.autos.alquilerautos.config;

import com.backend.apirest.autos.alquilerautos.repository.UsuarioRepository;
import com.backend.apirest.autos.alquilerautos.segurity.JwtTokenProvider;
import com.backend.apirest.autos.alquilerautos.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UsuarioRepository usuarioRepository;
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll() // Permitir acceso sin autenticación al endpoint de login
                .antMatchers(HttpMethod.GET, "/vehiculos/listar").permitAll() // Permitir acceso sin autenticación a /vehiculos/listar
                .antMatchers(HttpMethod.DELETE, "/vehiculos/eliminar/**").hasRole("ADMIN") // Restringir acceso a /vehiculos/eliminar/:id requiriendo autenticación y rol de administrador
                .antMatchers(HttpMethod.POST, "/vehiculos/agregar").hasRole("ADMIN") // Restringir acceso a /vehiculos/agregar requiriendo autenticación y rol de administrador
                .antMatchers(HttpMethod.POST, "/imagenes/agregar").hasRole("ADMIN") // Restringir acceso a /imagenes/agregar requiriendo autenticación y rol de administrador
                .antMatchers(HttpMethod.GET, "/imagenes/galeria").permitAll() // Permitir acceso sin autenticación a /imagenes/galeria
                .anyRequest().authenticated() // Restringir acceso a otras rutas que requieran autenticación
                .and()
                .addFilterBefore(new JwtFilter(jwtTokenProvider, customUserDetailsServiceBean(usuarioRepository)), UsernamePasswordAuthenticationFilter.class) // Agregar filtro JWT
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)); // Manejar la excepción de no autorizado
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsServiceBean(usuarioRepository)).passwordEncoder(passwordEncoder());
    }

    @Bean
    public CustomUserDetailsService customUserDetailsServiceBean(UsuarioRepository usuarioRepository) {
        return new CustomUserDetailsService(usuarioRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


