package com.Districto_Tech.distribuidora.features.providers.dto;

import com.Districto_Tech.distribuidora.common.IMapperConvertGlobalObjects;
import com.Districto_Tech.distribuidora.features.providers.dto.ProviderDTO;
import com.Districto_Tech.distribuidora.features.providers.entity.ProviderEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProviderMapper implements IMapperConvertGlobalObjects<ProviderEntity, ProviderDTO, ProviderDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProviderEntity toEntityRQ(ProviderDTO dto) {
        return modelMapper.map(dto, ProviderEntity.class);
    }

    @Override
    public ProviderEntity toEntityRS(ProviderDTO dto) {
        return modelMapper.map(dto, ProviderEntity.class);
    }

    @Override
    public ProviderDTO toResponseDto(ProviderEntity entity) {
        return modelMapper.map(entity, ProviderDTO.class);
    }

    @Override
    public ProviderDTO toRequestDto(ProviderEntity entity) {
        return modelMapper.map(entity, ProviderDTO.class);
    }
}