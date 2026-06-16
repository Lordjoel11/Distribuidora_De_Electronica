package com.Districto_Tech.distribuidora.features.employees.dtos;

import com.Districto_Tech.distribuidora.features.employees.misc.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequestDTO {
    @NotBlank(message = "El nombre es necesario.")
    private String name;
    @NotBlank(message = "El apellido es necesario.")
    private String surname;

    @NotBlank(message = "El CUIL es necesario.")
    @Pattern(regexp = "\\d{2}-\\d{8}-\\d{1}", message = "El CUIL debe tener el formato XX-XXXXXXXX-X")
    private String CUIL;

    @NotNull(message = "El rol es necesario.")
    private Role role;
}
