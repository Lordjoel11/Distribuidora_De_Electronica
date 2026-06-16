package com.Districto_Tech.distribuidora.features.shipping.dtos;

import com.Districto_Tech.distribuidora.features.shipping.misc.State;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShippingResponseDTO {
    private State state;
    private LocalDate shippingSentDate;
    private LocalDate shippingDeliveredDate;
}