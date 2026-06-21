package com.Districto_Tech.distribuidora.features.orders.dto;

import com.Districto_Tech.distribuidora.features.orders_details.dto.OrderDetailsRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequestDto {

    @NotNull(message = "El cliente es obligatorio.")
    private Long clientId;

    @NotNull(message = "El empleado es obligatorio.")
    private Long employeeId;

    @Valid
    @NotEmpty(message = "El pedido debe tener al menos un producto.")
    private List<OrderDetailsRequestDto> items;
}