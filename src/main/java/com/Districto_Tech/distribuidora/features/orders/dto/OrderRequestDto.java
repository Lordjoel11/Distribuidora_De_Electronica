package com.Districto_Tech.distribuidora.features.orders.dto;


import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
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

    @FutureOrPresent(message = "The order must be requested in a valid date.")
    private LocalDate orderDate;
}
