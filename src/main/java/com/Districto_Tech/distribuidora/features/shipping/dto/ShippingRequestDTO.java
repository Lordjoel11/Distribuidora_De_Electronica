package com.Districto_Tech.distribuidora.features.shipping.dto;

import com.Districto_Tech.distribuidora.features.clients.ClientEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jdk.jfr.Timespan;
import lombok.*;
import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShippingRequestDTO {


    @NotBlank (message = "El estado debe estar lleno.")
    private String status;

    @NotBlank (message = "El id debe ser valido.")

    private int idShipping;

    @FutureOrPresent (message = "Debe ser una fecha validad")

    LocalDate date;

    @NotBlank (message = "El cliente debe existir.")
    ClientEntity client;

}