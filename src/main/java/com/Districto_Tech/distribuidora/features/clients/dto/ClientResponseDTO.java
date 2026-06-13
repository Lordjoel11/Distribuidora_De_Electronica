package com.Districto_Tech.distribuidora.features.clients.dto;

import com.Districto_Tech.distribuidora.features.clients.Misc.TypeClient;

public record ClientResponseDTO(TypeClient typeClient,
                                Long phoneNumber,
                                String direction) {
}
