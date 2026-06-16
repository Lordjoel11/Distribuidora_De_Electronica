package com.Districto_Tech.distribuidora.features.invoices.dto;

import com.Districto_Tech.distribuidora.features.invoices.InvoiceType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceRequestDto {

    @NotNull(message = "Este campo no puede ser nulo.")
    private InvoiceType invoiceType;

    @NotNull(message = "Este campo no puede ser nulo.")
    private Long orderId;

    @NotNull(message = "Este campo no puede ser nulo.")
    @Positive(message = "Este campo tiene que ser positivo.")
    private Double total;
}