package com.Districto_Tech.distribuidora.features.clients.dto;

import jakarta.validation.constraints.NotNull;

public record ClientRequestDTO(String name,
                               String surname,
                               String DNI,
                               Long phoneNumber,
                               String address) {
}
