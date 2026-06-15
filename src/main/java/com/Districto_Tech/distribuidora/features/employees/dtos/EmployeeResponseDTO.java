package com.Districto_Tech.distribuidora.features.employees.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponseDTO {
    private Long idEmployee;
    private Long phoneNumber;
}
