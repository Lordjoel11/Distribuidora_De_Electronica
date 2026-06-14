package com.Districto_Tech.distribuidora.features.clients;

import com.Districto_Tech.distribuidora.common.IModelMapper;
import com.Districto_Tech.distribuidora.features.clients.dto.ClientRequestDTO;
import com.Districto_Tech.distribuidora.features.clients.dto.ClientResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClientModelMapper implements IModelMapper<ClientEntity, ClientResponseDTO, ClientRequestDTO> {

    private ModelMapper modelMapper;

    public ClientModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ClientEntity toEntity(ClientRequestDTO clientRequestDTO) {
        return modelMapper.map(clientRequestDTO, ClientEntity.class);
    }

    @Override
    public ClientResponseDTO toDto(ClientEntity clientEntity) {
        return modelMapper.map(clientEntity, ClientResponseDTO.class);
    }
}
