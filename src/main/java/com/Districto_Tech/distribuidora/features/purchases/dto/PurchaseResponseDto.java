package com.Districto_Tech.distribuidora.features.purchases.dto;

import com.Districto_Tech.distribuidora.features.purchases.PurchaseStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseResponseDto {

    private Long id;
    private LocalDate purchaseDate;
    private PurchaseStatus purchaseStatus;
    private String supplierName;
    private List<PurchaseDetailsResponseDto> purchaseDetails;
}