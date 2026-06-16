package com.Districto_Tech.distribuidora.features.orders_details.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailsResponseDto {

    private Long id;
    private String orderDescription;
    private Integer orderQuantity;
    private Double historicalPrice;
    private Long orderId;
    private Long productId;
}
