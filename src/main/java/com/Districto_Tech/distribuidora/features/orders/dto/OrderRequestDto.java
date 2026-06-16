package com.Districto_Tech.distribuidora.features.orders.dto;

import com.Districto_Tech.distribuidora.features.clients.ClientEntity;
import com.Districto_Tech.distribuidora.features.employees.EmployeeEntity;
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

    @NotNull(message = "The order must be related to an existing client.")
    private ClientEntity clientEntity;

    @NotNull(message = "The order must be related to an existing employee.")
    private EmployeeEntity employeeEntity;

    @Valid
    @NotEmpty(message = "The order must have at least one detail.")
    private List<OrderDetailsRequestDto> orderDetails;




}
