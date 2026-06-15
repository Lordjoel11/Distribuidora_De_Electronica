package com.Districto_Tech.distribuidora.features.employees.dtos;

import com.Districto_Tech.distribuidora.features.employees.misc.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequestDTO {
    @NotBlank(message = "The name is necessary")
    private String name;
    @NotBlank(message = "The surname is necessary")
    private String surname;
    @NotBlank(message = "The CUIL is necessary")
    private Long CUIL;
    @NotBlank(message = "The role is necessary")
    private Role role;
}
