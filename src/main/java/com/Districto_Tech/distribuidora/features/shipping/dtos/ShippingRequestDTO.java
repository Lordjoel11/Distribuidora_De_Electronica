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
    @NotNull(message = "Este campo no puede ser nulo.")
    private UUID uuid;

    @NotNull(message = "Este campo no puede ser nulo.")
    @FutureOrPresent(message = "La fecha debe a futura o presente")
    private LocalDate shippingSentDate;

    @NotNull(message = "Este campo no puede ser nulo.")
    @PastOrPresent(message = "La fecha debe ser del pasado o presente.")
    private LocalDate shippingDeliveredDate;
}
