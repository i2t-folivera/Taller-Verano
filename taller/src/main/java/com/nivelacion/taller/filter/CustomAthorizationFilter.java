package com.nivelacion.taller.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import org.springframework.http.MediaType;

@Slf4j
public class CustomAthorizationFilter extends OncePerRequestFilter {

    // @Override
    // protected void doFilterInternal(HttpServletRequest request,
    // HttpServletResponse response, FilterChain filterChain)
    // throws ServletException, IOException {

    // // Configurar los encabezados CORS para todas las solicitudes
    // response.setHeader("Access-Control-Allow-Origin", "*");
    // response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS,
    // DELETE, PUT");
    // response.setHeader("Access-Control-Allow-Headers", "Authorization,
    // Content-Type, Accept");
    // response.setHeader("Access-Control-Max-Age", "3600");

    // if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
    // // Si es una solicitud OPTIONS, responder con un código 200
    // response.setStatus(HttpServletResponse.SC_OK);
    // } else {
    // // Si no es una solicitud OPTIONS, continuar con la lógica de autenticación y
    // // autorización
    // String authorizationHeader = request.getHeader(AUTHORIZATION);
    // if (request.getRequestURI().equals("/api/v1/login")) {
    // // Si la solicitud es para el endpoint de inicio de sesión, permitir el
    // acceso
    // // sin autenticación
    // filterChain.doFilter(request, response);
    // } else if (authorizationHeader != null &&
    // authorizationHeader.startsWith("Bearer ")) {
    // try {
    // String token = authorizationHeader.substring("Bearer ".length());
    // Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
    // JWTVerifier verifier = JWT.require(algorithm).build();
    // DecodedJWT decodedJWT = verifier.verify(token);
    // String username = decodedJWT.getSubject();
    // String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
    // Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    // Arrays.stream(roles).forEach(role -> {
    // authorities.add(new SimpleGrantedAuthority(role));
    // });
    // UsernamePasswordAuthenticationToken authenticationToken = new
    // UsernamePasswordAuthenticationToken(
    // username, null, authorities);
    // SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    // filterChain.doFilter(request, response);
    // } catch (Exception exception) {
    // log.error("Error en el logging en: {}", exception.getMessage());
    // response.setHeader("error", exception.getMessage());
    // response.setStatus(HttpStatus.FORBIDDEN.value());
    // Map<String, String> error = new HashMap<>();
    // error.put("error_message", exception.getMessage());
    // response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    // new ObjectMapper().writeValue(response.getOutputStream(), error);
    // }
    // } else {
    // // Si no hay token de autorización, devolver un código de estado de no
    // // autorizado
    // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    // }
    // }
    // }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Configurar los encabezados CORS para todas las solicitudes
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
        response.setHeader("Access-Control-Max-Age", "3600");

        if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
            // Si es una solicitud OPTIONS, responder con un código 200
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            // Si no es una solicitud OPTIONS, continuar con la lógica de autenticación y
            // autorización
            String requestURI = request.getRequestURI();
            if (requestURI.equals("/api/v1/login") || requestURI.equals("/api/v1/usuario/save")) {
                // Si la solicitud es para el endpoint de inicio de sesión o registro de
                // usuario,
                // permitir el acceso sin autenticación
                filterChain.doFilter(request, response);
            } else {
                String authorizationHeader = request.getHeader(AUTHORIZATION);
                if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                    try {
                        String token = authorizationHeader.substring("Bearer ".length());
                        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                        JWTVerifier verifier = JWT.require(algorithm).build();
                        DecodedJWT decodedJWT = verifier.verify(token);
                        String username = decodedJWT.getSubject();
                        String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                        Arrays.stream(roles).forEach(role -> {
                            authorities.add(new SimpleGrantedAuthority(role));
                        });
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                username, null, authorities);
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                        filterChain.doFilter(request, response);
                    } catch (Exception exception) {
                        log.error("Error en el logging en: {}", exception.getMessage());
                        response.setHeader("error", exception.getMessage());
                        response.setStatus(HttpStatus.FORBIDDEN.value());
                        Map<String, String> error = new HashMap<>();
                        error.put("error_message", exception.getMessage());
                        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                        new ObjectMapper().writeValue(response.getOutputStream(), error);
                    }
                } else {
                    // Si no hay token de autorización y no es una solicitud para el inicio de
                    // sesión o registro de usuario,
                    // devolver un código de estado de no autorizado
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }
        }
    }

}
