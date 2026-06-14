package com.Districto_Tech.distribuidora.features.suppliers.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierResponseDto {

    private Long id;
    private String name;
    private String cuit;
    private String contact;
}