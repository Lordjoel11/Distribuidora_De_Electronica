package com.Districto_Tech.distribuidora.features.suppliers.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierRequestDto {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 40, message = "El nombre debe tener entre 2 y 40 caracteres")
    private String name;

    @NotBlank(message = "El CUIT es obligatorio")
    @Pattern(regexp = "\\d{2}-\\d{8}-\\d{1}", message = "El CUIT debe tener el formato XX-XXXXXXXX-X")
    private String cuit;

    @Size(max = 255, message = "El contacto no puede superar los 255 caracteres")
    private String contact;
}