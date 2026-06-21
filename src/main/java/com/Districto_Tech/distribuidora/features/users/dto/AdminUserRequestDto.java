package com.Districto_Tech.distribuidora.features.users.dto;

import com.Districto_Tech.distribuidora.features.users.RoleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminUserRequestDto {

    @NotBlank(message = "El email es requerido.")
    @Email(message = "El formato del email no es válido.")
    private String email;

    @NotBlank(message = "La contraseña es requerida.")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres.")
    private String password;

    @NotNull(message = "El rol es requerido.")
    private RoleType roleType;
}