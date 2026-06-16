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

    @NotNull(message = "Este campo no puede ser nulo.")
    private Long clientId;

    @NotNull(message = "Este campo no puede ser nulo.")
    private Long employeeId;

    @Valid
    @NotEmpty(message = "Este campo no puede estar vacio.")
    private List<OrderDetailsRequestDto> orderDetails;




}
