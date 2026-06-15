package com.Districto_Tech.distribuidora.features.clients.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ClientRequestDTO {

    @NotBlank(message = "The name is necessary")
    @NotNull(message = "Name cannot be null")
    private String name;
    @NotBlank(message = "The DNI is necessary")
    @NotNull(message = "The DNI cannot be null")
    private String DNI;
    @NotBlank(message = "The phone number is necessary")
    @NotNull(message = "The phone number cannot be null")
    private Long phoneNumber;
    @NotBlank(message = "The address is necessary")
    @NotNull(message = "The address cannot be null")
    private String address;

}
