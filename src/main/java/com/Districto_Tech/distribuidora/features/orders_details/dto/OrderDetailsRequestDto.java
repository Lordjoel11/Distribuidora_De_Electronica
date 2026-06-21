package com.Districto_Tech.distribuidora.features.orders_details.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailsRequestDto {

    @NotNull(message = "El producto es obligatorio.")
    private Long productId;

    @NotNull(message = "La cantidad es obligatoria.")
    @Positive(message = "La cantidad debe ser mayor a 0.")
    private Integer quantity;
}