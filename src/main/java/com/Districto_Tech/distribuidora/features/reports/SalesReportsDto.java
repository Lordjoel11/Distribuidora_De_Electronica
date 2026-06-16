package com.Districto_Tech.distribuidora.features.reports;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesReportsDto {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private long cantidadPedidos;
    private double montoTotal;
}