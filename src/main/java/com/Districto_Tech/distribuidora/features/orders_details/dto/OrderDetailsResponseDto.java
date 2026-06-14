package com.Districto_Tech.distribuidora.features.orders_details.dto;

import com.Districto_Tech.distribuidora.features.orders.OrderEntity;
import com.Districto_Tech.distribuidora.features.products.Product;
import org.hibernate.query.Order;

public class OrderDetailsResponseDto {

    private String description;
    private Integer quantity;
    private Double historicalPrice;
}
