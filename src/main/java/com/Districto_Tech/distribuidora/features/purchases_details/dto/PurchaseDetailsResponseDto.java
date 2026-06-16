package com.Districto_Tech.distribuidora.features.purchases_details.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseDetailsResponseDto {

    private Long id;
    private Integer quantity;
    private Long productId;
    private String productName;
}