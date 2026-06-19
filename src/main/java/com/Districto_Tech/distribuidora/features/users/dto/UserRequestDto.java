package com.Districto_Tech.distribuidora.features.users.dto;

import com.Districto_Tech.distribuidora.features.users.RoleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {

    @NotBlank(message = "El email es requerido.")
    @Email(message = "Debe ser formato Email.")
    private String email;

    @NotBlank(message = "La contraseña es requerido.")
    private String password;

    @NotNull(message = "El email no puede ser nulo.")
    private RoleType roleType;

}
