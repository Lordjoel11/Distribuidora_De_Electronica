package com.Districto_Tech.distribuidora.features.clients.dto;

import com.Districto_Tech.distribuidora.features.clients.Misc.TypeClient;
import com.Districto_Tech.distribuidora.features.users.UserEntity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseDTO {
    private String local_name;
    private String CUIT;
    private String phoneNumber;
    private String address;
    private boolean isVip;
}
