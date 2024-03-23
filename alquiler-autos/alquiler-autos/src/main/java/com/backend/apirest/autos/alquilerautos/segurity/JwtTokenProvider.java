package com.backend.apirest.autos.alquilerautos.segurity;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken(UserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(userDetails.getUsername()) // Configura el correo electrónico como el sujeto del token
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Authentication getAuthentication(String token, UserDetailsService userDetailsService) {
        String username = getUsername(token);
        System.out.println("username"+username);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username); // Carga el usuario por su correo electrónico
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    private String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .get("email", String.class); // Extraer el valor del campo "email"
    }

    public boolean validateToken(String authToken) {
        try {
            System.out.println("vvv"+Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken));
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            // handle JWT signature exception
            ex.printStackTrace();
        } catch (MalformedJwtException ex) {
            // handle invalid JWT token
            ex.printStackTrace();
        } catch (ExpiredJwtException ex) {
            // handle expired JWT token
            ex.printStackTrace();
        } catch (UnsupportedJwtException ex) {
            // handle unsupported JWT token
            ex.printStackTrace();
        } catch (IllegalArgumentException ex) {
            // handle JWT claims string is empty
            ex.printStackTrace();
        }
        return false;
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        System.out.println(bearerToken);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            System.out.println(bearerToken.substring(7));
            return bearerToken.substring(7);
        }
        return null;
    }
}

