package com.Districto_Tech.distribuidora.features.clients.dto;

import com.Districto_Tech.distribuidora.features.clients.Misc.TypeClient;
import com.Districto_Tech.distribuidora.features.users.UserEntity;

public record ClientResponseDTO(TypeClient typeClient,
                                UserEntity userEntity) {




}
