package com.Districto_Tech.distribuidora.features.shipping.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;


@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShippingRequestDTO {
    @NotNull(message = "The uuid can't be null")
    private UUID uuid;

    @NotNull(message = "The sent date is necessary")
    @FutureOrPresent(message = "La fecha debe a futura o presente")
    private LocalDate shippingSentDate;

    @NotNull(message = "The delivered date is necessary")
    @PastOrPresent(message = "La fecha debe ser del pasado o presente.")
    private LocalDate shippingDeliveredDate;
}
