package com.Districto_Tech.distribuidora.features.clients;

import com.Districto_Tech.distribuidora.common.IMapper;
import com.Districto_Tech.distribuidora.features.clients.dto.ClientRequestDTO;
import com.Districto_Tech.distribuidora.features.clients.dto.ClientResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

@Component
public class ClientMapper implements IMapper<ClientEntity, ClientResponseDTO, ClientRequestDTO> {

    private ModelMapper modelMapper;

    public ClientMapper(ModelMapper modelMapper) {
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
