package com.Districto_Tech.distribuidora.features.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {

    @NotBlank(message = "El email es requerido.")
    @Email(message = "El formato del email no es válido.")
    private String email;

    @NotBlank(message = "La contraseña es requerida.")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres.")
    private String password;

    @NotBlank(message = "El nombre y apellido es obligatorio.")
    private String nameAndSurname;

    @NotBlank(message = "El DNI es obligatorio.")
    private String DNI;

    @NotBlank(message = "El teléfono es obligatorio.")
    private String phoneNumber;

    @NotBlank(message = "La dirección es obligatoria.")
    private String address;
}