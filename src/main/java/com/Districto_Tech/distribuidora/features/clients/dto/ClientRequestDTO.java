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

    @NotBlank(message = "The name and surname is necessary")
    private String nameAndSurname;

    @NotBlank(message = "The DNI is necessary")
    private String DNI;

    @NotBlank(message = "The Email is necessary")
    @Email(message = "El formato debe contener @gmail.com.")
    private String Email;

    @NotBlank(message = "The phone number is necessary")
    private String phoneNumber;

    @NotBlank(message = "The address is necessary")
    private String address;

    private boolean isVip;

}
