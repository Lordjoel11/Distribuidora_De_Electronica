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

    @NotBlank(message = "El nombre y apellido es requerido.")
    private String nameAndSurname;

    @NotBlank(message = "El DNI es requerido.")
    private String DNI;

    @NotBlank(message = "El numero del local es requerido.")
    private String phoneNumber;

    @NotBlank(message = "La direccion de local es requerido.")
    private String address;

    private boolean isVip;

}
