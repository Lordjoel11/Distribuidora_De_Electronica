package com.Districto_Tech.distribuidora.features.orders_details.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailsRequestDto {

    @NotNull(message = "The product ID must be provided.")
    private Long productId;

    @NotBlank(message = "A description must be given.")
    private String orderDescription;

    @NotNull(message = "A quantity must be given.")
    private Integer orderQuantity;
}
