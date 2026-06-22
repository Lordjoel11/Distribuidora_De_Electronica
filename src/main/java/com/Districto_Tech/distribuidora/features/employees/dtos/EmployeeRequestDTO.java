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

    @NotNull(message = "El ID de usuario es obligatorio.")
    private Long userId;

    @NotBlank(message = "El nombre es necesario.")
    private String name;

    @NotBlank(message = "El apellido es necesario.")
    private String surname;

    @NotBlank(message = "El CUIL es necesario.")

    private String CUIL;

    @NotBlank(message = "El teléfono es necesario.")
    private String phoneNumber;

    @NotNull(message = "El rol es necesario.")
    private Role role;
}