package com.Districto_Tech.distribuidora.features.users.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsRequest {
    @NotBlank(message = "El email es requerido.")
    @Email(message = "El formato del email no es válido.")
    private String username;

    @NotBlank(message = "La contraseña es requerida.")
    private String password;
}