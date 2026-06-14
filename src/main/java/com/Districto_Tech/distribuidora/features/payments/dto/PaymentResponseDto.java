package com.Districto_Tech.distribuidora.features.payments.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponseDto {

    private Long id;
    private Double amount;
    private LocalDateTime date;
    private String paymentMethod;
    private String discountType;
    private Double discountPercentage;
    private Long orderId;
}