package com.Districto_Tech.distribuidora.features.payment.dto;

import com.Districto_Tech.distribuidora.features.payment.entity.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class PaymentResponseDTO {

    private String publicID;
    private PaymentType paymentType;
    private int idPayment;
    private double amount;
    private LocalDate date;
}
