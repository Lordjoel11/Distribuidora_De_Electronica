package com.Districto_Tech.distribuidora.features.payments.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequestDto {

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor a 0")
    private Double amount;

    @NotNull(message = "El método de pago es obligatorio")
    private Long paymentMethodId;

    @NotNull(message = "El tipo de descuento es obligatorio")
    private Long discountTypeId;

    @NotNull(message = "El pedido es obligatorio")
    private Long orderId;
}