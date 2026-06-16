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

    @NotNull(message = "The invoice type is required.")
    private InvoiceType invoiceType;

    @NotNull(message = "The order ID is required.")
    private Long orderId;

    @NotNull(message = "The total is required.")
    @Positive(message = "The total must be greater than 0.")
    private Double total;
}