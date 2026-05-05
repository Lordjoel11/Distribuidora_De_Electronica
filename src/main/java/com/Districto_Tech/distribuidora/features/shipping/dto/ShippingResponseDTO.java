package com.Districto_Tech.distribuidora.features.shipping.dto;

import com.Districto_Tech.distribuidora.features.clients.ClientEntity;
import com.Districto_Tech.distribuidora.features.shipping.entity.ShippingStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ShippingResponseDTO {
    private String publicId;
    private String status;
    private int idShipping;
    private LocalDate date;
    private ClientEntity client;
}