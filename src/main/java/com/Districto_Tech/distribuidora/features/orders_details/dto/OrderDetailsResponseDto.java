package com.Districto_Tech.distribuidora.features.orders_details.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailsResponseDto {

    private Long id;
    private Integer quantity;
    private Double historicalPrice;
    private Long productId;
    private String productName;
    private Long orderId;
}