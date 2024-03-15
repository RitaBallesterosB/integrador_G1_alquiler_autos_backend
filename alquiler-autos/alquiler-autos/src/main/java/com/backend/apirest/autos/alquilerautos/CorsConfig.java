package com.backend.apirest.autos.alquilerautos;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        // Configuración CORS para el endpoint de vehículos
        registry.addMapping("/vehiculos/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");

        // Configuración CORS para el endpoint de imágenes
        registry.addMapping("/imagenes/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");

        // Configuración CORS para el endpoint de categorías
        registry.addMapping("/categorias/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");

        // Configuración CORS para el endpoint de login

        registry.addMapping("/login/**");

        registry.addMapping("/login")

                .allowedOrigins("http://localhost:5173")
                .allowedMethods("POST") // Generalmente, el endpoint de login solo acepta solicitudes POST
                .allowedHeaders("*");


    }


}



