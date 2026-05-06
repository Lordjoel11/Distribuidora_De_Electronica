package com.Districto_Tech.distribuidora.features.providers.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProviderDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String contacto;

    private String telefono;

    @Email(message = "Debe ser un email válido")
    private String email;
}