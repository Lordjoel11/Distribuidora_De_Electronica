package com.Districto_Tech.distribuidora.features.employees.dtos;

import com.Districto_Tech.distribuidora.features.employees.misc.Role;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponseDTO {
    private String name;
    private String surname;
    private String phoneNumber;
    private String CUIL;
    private Role role;
}
