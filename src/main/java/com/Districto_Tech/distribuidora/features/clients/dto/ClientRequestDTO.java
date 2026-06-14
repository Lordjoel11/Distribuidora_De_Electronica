package com.Districto_Tech.distribuidora.features.clients.dto;

import com.Districto_Tech.distribuidora.features.clients.Misc.TypeClient;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDTO {
    @NotBlank(message = "El nombre del local es requerido.")
    private String local_name;

    @NotBlank(message = "El nombre del local es requerido.")
    private String CUIT;

    @NotBlank(message = "El numero del local es requerido.")
    private String phoneNumber;
    @NotBlank(message = "La direccion de local es requerido.")
    private String address;

    private boolean isVip;

}
