package com.arriendatufinca.arriendatufinca.Services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    // Clave fija y consistente entre generación y validación
    private static final String SECRET = "MySEcretKeyForJwtGenerationAndValidation12345"; // Debe ser al menos 256 bits
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
    private static final long EXPIRATION_TIME_MS = 3600000;

    public String generateToken(String correo) {
        return Jwts.builder()
                .setSubject(correo.trim().toLowerCase())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS))
                .signWith(SECRET_KEY)
                .compact();
    }

    public String extractCorreo(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String validateToken(String token) {
        return extractCorreo(token);
    }
}