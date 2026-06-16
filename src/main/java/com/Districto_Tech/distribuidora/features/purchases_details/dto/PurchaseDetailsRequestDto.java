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

    @NotNull(message = "The product ID is required.")
    private Long productId;

    @NotNull(message = "The quantity is required.")
    @Positive(message = "The quantity must be greater than 0.")
    private Integer quantity;
}