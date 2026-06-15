package com.Districto_Tech.distribuidora.common.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilitamos CSRF porque usamos tokens JWT (no manejamos cookies/sesiones estatales)
                .csrf(csrf -> csrf.disable())

                // Configuramos las reglas de los endpoints (Tu matriz de roles de la distribuidora)
                .authorizeHttpRequests(auth -> auth



                                .requestMatchers("/api/v1/auth/**").permitAll()
                                .requestMatchers("/h2-console/**").permitAll()

                                // ✅ Rutas reales del proyecto
                                .requestMatchers("/api/clients/**").hasRole("ADMIN")
                                .requestMatchers("/api/users/**").hasRole("ADMIN")
                                 .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api/employees/**").hasAnyRole("ADMIN", "EMPLEADO")
                                .requestMatchers("/api/suppliers/**").hasAnyRole("ADMIN", "EMPLEADO")
                                .requestMatchers("/api/payments/**").hasAnyRole("ADMIN", "EMPLEADO")
                                .requestMatchers("/api/products/**").hasAnyRole("ADMIN", "EMPLEADO", "CLIENT")
                                .requestMatchers("/api/orders/**").hasAnyRole("ADMIN", "EMPLEADO", "CLIENT")
                                .requestMatchers("/api/order-details/**").hasAnyRole("ADMIN", "EMPLEADO", "CLIENT")

                                .anyRequest().authenticated()

                )
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpStatus.FORBIDDEN.value());
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write(
                                    "{\"status\": 403, \"error\": \"Forbidden\", \"message\": \"No tenés permisos para acceder a este recurso.\", \"path\": \"" + request.getRequestURI() + "\"}"
                            );
                        })
                )

                // Política de sesión STATELESS (Sin estado): cada request debe traer su token
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Le decimos qué proveedor de autenticación usar
                .authenticationProvider(authenticationProvider)

                // Metemos nuestro filtro JWT antes del filtro de autenticación por defecto de Spring
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

                 http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}