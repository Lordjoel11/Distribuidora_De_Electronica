package com.Districto_Tech.distribuidora.features.invoices.dto;

import com.Districto_Tech.distribuidora.features.invoices.InvoiceType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceResponseDto {

    private Long id;
    private InvoiceType invoiceType;
    private LocalDateTime date;
    private Double total;
    private Long orderId;
}