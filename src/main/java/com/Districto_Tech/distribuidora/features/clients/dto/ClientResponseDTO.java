package com.Districto_Tech.distribuidora.features.clients.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseDTO {
    private String nameAndSurname;
    private String DNI;
    private String email;
    private String phoneNumber;
    private String address;
    private boolean isVip;
}
