package com.Districto_Tech.distribuidora.features.orders.dto;


import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class OrderRequestDto {

//    @NotNull(message = "The order must be related to an existing client.")
//    private ClientEntity clientEntity;
//
//    @NotNull(message = "The order must be related to an existing employee.")
//    private EmployeeEntity employeeEntity;

    @NotNull(message = "The order must have a valid code")
    private UUID orderCode;




}
