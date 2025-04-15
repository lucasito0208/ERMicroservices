package com.lucasDev.auth.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.IOException;

@Component
public class JwtFilter extends HttpFilter {

    private final UserDetailsService userDetailsService;
    
    private static final String SECRET_KEY = "${security.jwt.secret}"; 

    public JwtFilter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilter(@Nonnull HttpServletRequest request,
                            @Nonnull HttpServletResponse response,
                            @Nonnull FilterChain filterChain) throws IOException, ServletException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Verifica si el header tiene el formato correcto
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7); // Extraemos solo el token

        try {
            Claims claims = extractClaims(token);
            String username = claims.getSubject();

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        } catch (JwtException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token JWT inválido o expirado");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())  // Usamos SecretKey correctamente
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }
}
