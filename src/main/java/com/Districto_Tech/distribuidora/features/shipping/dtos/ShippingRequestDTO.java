package com.Districto_Tech.distribuidora.features.shipping.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShippingRequestDTO {
    @NotNull(message = "The uuid can't be null")
    private UUID uuid;
    @NotBlank(message = "The sent date is necessary")
    private LocalDate shippingSentDate;
    @NotBlank(message = "The delivered date is necessary")
    private LocalDate shippingDeliveredDate;
}
