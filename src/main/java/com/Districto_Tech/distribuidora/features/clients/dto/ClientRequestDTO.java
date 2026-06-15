package com.Districto_Tech.distribuidora.features.clients.dto;

import com.Districto_Tech.distribuidora.features.clients.Misc.TypeClient;

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

    @NotBlank(message = "The phone number is necessary")
    private String phoneNumber;

    @NotBlank(message = "The address is necessary")
    private String address;

    @NotBlank(message = "Whether the client is vip or not is necessary")
    private boolean isVip;

}
