package com.Districto_Tech.distribuidora.features.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {
    //Esta clase es para el sistema
    private String publicId;

    @NotBlank(message =  "El Email es obligatorio")
    @Email(message = "El email debe ser valido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe al menos tener 8 caracteres.")
    private String password;

    @NotBlank(message = "El rol es obligatorio")
    private String rol;

}
