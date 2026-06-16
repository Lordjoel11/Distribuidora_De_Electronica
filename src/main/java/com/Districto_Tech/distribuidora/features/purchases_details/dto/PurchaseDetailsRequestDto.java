package com.Districto_Tech.distribuidora.features.purchases_details.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseDetailsRequestDto {

    @NotNull(message = "Este campo no puede ser nulo.")
    private Long productId;

    @NotNull(message = "Este campo no puede ser nulo.")
    @Positive(message = "Debe ser positivo.")
    private Integer quantity;
}