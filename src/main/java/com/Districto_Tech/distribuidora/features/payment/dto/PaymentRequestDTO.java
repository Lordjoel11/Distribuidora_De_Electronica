package com.Districto_Tech.distribuidora.features.payment.dto;

import com.Districto_Tech.distribuidora.features.payment.entity.PaymentType;
import jakarta.validation.MessageInterpolator;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PaymentRequestDTO {

    @NotBlank (message = "El metodo de pago no debe estar vacio.")

    private PaymentType paymentType;

    @NotBlank (message = "El ID no debe estar vacio.")

    private int idPayment;

    @NotBlank (message = "El pago no debe estar vacio.")

    private double amount;

    @FutureOrPresent (message = ("La fecha debe ser valida."))

    private LocalDate date;

}
