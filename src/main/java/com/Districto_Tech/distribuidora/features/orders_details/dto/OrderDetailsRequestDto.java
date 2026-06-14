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

    @NotBlank (message = "A description of the product must be given.")
    private String description;

    @NotNull (message = "The description must have a valid ID")
    private UUID publicId;

    @NotNull (message = "A product quantity must be given.")
    private Integer quantity;


}
