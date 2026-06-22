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
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        // Publico
                        .requestMatchers("/test").permitAll()
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()

                        // Rutas de "mis datos"
                        .requestMatchers("/api/payments/my").hasAnyRole("ADMIN", "EMPLOYEE", "CLIENT")
                        .requestMatchers("/api/invoices/my").hasAnyRole("ADMIN", "EMPLOYEE", "CLIENT")

                        //ADMIN
                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/users/**").hasRole("ADMIN")
                        .requestMatchers("/api/purchases/**").hasRole("ADMIN")
                        .requestMatchers("/api/clients/**").hasRole("ADMIN")

                        // ADMIN y EMPLOYEE
                        .requestMatchers("/api/payments/**").hasAnyRole("ADMIN", "EMPLOYEE")
                        .requestMatchers("/api/employees/**").hasAnyRole("ADMIN", "EMPLOYEE")
                        .requestMatchers("/api/suppliers/**").hasAnyRole("ADMIN", "EMPLOYEE")
                        .requestMatchers("/api/invoices/**").hasAnyRole("ADMIN", "EMPLOYEE")

                        // ADMIN, EMPLOYEE y CLIENT
                        .requestMatchers("/api/products/**").hasAnyRole("ADMIN", "EMPLOYEE", "CLIENT")
                        .requestMatchers("/api/orders/**").hasAnyRole("ADMIN", "EMPLOYEE", "CLIENT")
                        .requestMatchers("/api/order-details/**").hasAnyRole("ADMIN", "EMPLOYEE", "CLIENT")

                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}