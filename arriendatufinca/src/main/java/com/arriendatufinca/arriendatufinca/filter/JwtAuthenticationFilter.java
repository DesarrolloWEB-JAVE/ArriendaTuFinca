package com.arriendatufinca.arriendatufinca.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.arriendatufinca.arriendatufinca.Entities.User;
import com.arriendatufinca.arriendatufinca.Repositories.UserRepository;
import com.arriendatufinca.arriendatufinca.Services.JwtService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SecurityException;
import java.util.List;
import java.util.Optional;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository UserRepository;

    public JwtAuthenticationFilter(JwtService jwtService, UserRepository UserRepository) {
        this.jwtService = jwtService;
        this.UserRepository = UserRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
        throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring(7);
        String correo;

        try {
            correo = jwtService.extractCorreo(jwt).trim().toLowerCase();
        } catch (ExpiredJwtException | SecurityException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        if (correo != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<User> User = UserRepository.findByEmail(correo);
            if (User != null) {
                UsernamePasswordAuthenticationToken authToken =
        new UsernamePasswordAuthenticationToken(correo, null, List.of());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                System.out.println("User no encontrado en la BD: " + correo);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}