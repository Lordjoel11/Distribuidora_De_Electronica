package com.Districto_Tech.distribuidora.features.orders.dto;

import com.Districto_Tech.distribuidora.features.orders.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder

public class OrderResponseDto {
    private LocalDate orderDate;
    private Status orderStatus;
    
}
