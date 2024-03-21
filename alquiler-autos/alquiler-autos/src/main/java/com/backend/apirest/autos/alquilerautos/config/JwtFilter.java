package com.backend.apirest.autos.alquilerautos.config;

import com.backend.apirest.autos.alquilerautos.exceptions.JwtAuthenticationException;
import com.backend.apirest.autos.alquilerautos.segurity.JwtTokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class JwtFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    // Rutas que no requieren autenticación
    private static final Set<String> UNAUTHENTICATED_PATHS = new HashSet<>(Arrays.asList(
                    "/login",
                    "/vehiculos/listar",
                    "/vehiculos/aleatorios",
                    "/vehiculos/**",
                    "/imagenes/galeria/**",
                    "/imagenes/galeria/**/vermas",
                    "/usuarios/registro",
                    "vehiculos/busqueda"));

    public JwtFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        String token = jwtTokenProvider.resolveToken(httpRequest);

         //Verificar si la ruta no requiere autenticación
        if (!requiresAuthentication(httpRequest.getRequestURI())) {
            filterChain.doFilter(req, res);
           return;
        }



        // Verificar si el token está presente en la solicitud
        if (token != null) {
            try {
                if (jwtTokenProvider.validateToken(token)) {
                    Authentication auth = jwtTokenProvider.getAuthentication(token, userDetailsService);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (JwtAuthenticationException e) {
                // Manejar la excepción de autenticación JWT
                SecurityContextHolder.clearContext();
                HttpServletResponse httpResponse = (HttpServletResponse) res;
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT token is expired or invalid");
                return;
            }
        } else {
            // Token no presente en la solicitud
            SecurityContextHolder.clearContext();
            HttpServletResponse httpResponse = (HttpServletResponse) res;
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT token is missing");
            return;
        }

        // Continuar con la cadena de filtros
        filterChain.doFilter(req, res);
    }


    // Método para verificar si la ruta requiere autenticación MODIFICADO POR RITA


    // Método para verificar si la ruta requiere autenticación
    private boolean requiresAuthentication(String requestURI) {
        for (String path : UNAUTHENTICATED_PATHS) {
            if (requestURI.startsWith(path)) {
                // Si la ruta comienza con una de las rutas no autenticadas, devolver falso
                return false;
            } else if (path.contains("**")) {
                // Si la ruta en UNAUTHENTICATED_PATHS contiene "**", verificar si coincide con la parte de la ruta antes del "**"
                String prefix = path.substring(0, path.indexOf("**"));
                if (requestURI.startsWith(prefix)) {
                    return false;
                }
            }
        }
        return true;
    }





}

