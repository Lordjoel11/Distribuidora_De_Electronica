package com.Districto_Tech.distribuidora.features.users.dto;

import com.Districto_Tech.distribuidora.features.users.RoleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {

    @NotBlank(message = "El email es requerido.")
    @NotNull(message = "El email no puede ser nulo.")
    private String email;

    @NotBlank(message = "La contraseña es requerido.")
    @NotNull(message = "La contraseña no puede ser nulo.")
    private String password;

    @NotNull(message = "El email no puede ser nulo.")
    private RoleType roleType;



}
