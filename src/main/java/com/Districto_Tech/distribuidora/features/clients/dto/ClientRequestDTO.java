package com.Districto_Tech.distribuidora.features.clients.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDTO {

    @NotBlank(message = "Este campo no puede estar en blanco.")
    private String nameAndSurname;

    @NotBlank(message = "Este campo no puede estar en blanco.")
    private String DNI;

    @NotBlank(message = "Este campo no puede estar en blanco.")
    @Email(message = "El formato debe contener formato mail")
    private String Email;

    @NotBlank(message = "Este campo no puede estar en blanco.")
    private String phoneNumber;

    @NotBlank(message = "Este campo no puede estar en blanco.")
    private String address;

    private boolean isVip;

}
