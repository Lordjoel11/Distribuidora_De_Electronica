package com.Districto_Tech.distribuidora.features.orders.dto;

import com.Districto_Tech.distribuidora.features.orders.Status;
import com.Districto_Tech.distribuidora.features.orders_details.dto.OrderDetailsResponseDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {

    private Long id;
    private UUID orderCode;
    private LocalDate orderDate;
    private Status orderStatus;
    private Long clientId;
    private String clientName;
    private Long employeeId;
    private String employeeName;
    private List<OrderDetailsResponseDto> items;
    private Double total;
}