package com.Districto_Tech.distribuidora.features.shipping.dto;

import com.Districto_Tech.distribuidora.common.IMapperConvertGlobalObjects;
import com.Districto_Tech.distribuidora.features.shipping.entity.ShippingEntity;
import com.Districto_Tech.distribuidora.features.users.dto.UserRequestDto;
import com.Districto_Tech.distribuidora.features.users.entities.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.stereotype.Component;

@Component
public class ShippingMapper implements IMapperConvertGlobalObjects<ShippingEntity, ShippingRequestDTO, ShippingResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ShippingResponseDTO toResponseDto(ShippingEntity entity) {
        return null;
    }

    @Override
    public ShippingRequestDTO toRequestDto(ShippingEntity entity) {
        return null;
    }

    @Override
    public ShippingEntity toEntityRS(ShippingResponseDTO responseDto) {
        return null;
    }

    @Override
    public ShippingEntity toEntityRQ(ShippingRequestDTO requestDto) {
        return null;
    }
}