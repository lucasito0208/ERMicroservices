package com.lucasDev.auth.config;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

import java.security.Key;
import java.sql.Date;

import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${security.jwt.secret}")
    private String secretKey;

    @Value("${security.jwt.expiration}")
    private long expirationTime;

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }


    // 🔹 Generar Token JWT
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis())) // Corregido
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSignKey()) // Eliminamos el algoritmo como parámetro
                .compact();
    }

    // 🔹 Validar Token JWT
    public boolean validateToken(String token) {
        try {
            Jwts.parser() 
                .verifyWith(getSignKey())  // Método corregido
                .build()
                .parse(token); // `parseSignedClaims(token)` ya no es necesario
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // 🔹 Extraer Claims del Token
    public Claims getClaims(String token) {
        return (Claims) Jwts.parser()
                .verifyWith(getSignKey()) // Método corregido
                .build()
                .parse(token) // `parseSignedClaims(token)` ya no es necesario
                .getPayload();
    }

    // 🔹 Obtener clave secreta en formato Key
    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)); // Corregido
    }
    
}
