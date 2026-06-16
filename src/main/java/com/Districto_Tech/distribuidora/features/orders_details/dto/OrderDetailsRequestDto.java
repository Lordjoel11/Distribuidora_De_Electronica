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

    @NotNull(message = "Este campo no puede ser nulo.")
    private Long productId;

    @NotBlank(message = "Este campo no puede estar en blanco.")
    private String orderDescription;

    @NotNull(message = "Este campo no puede ser nulo.")
    private Integer orderQuantity;
}
