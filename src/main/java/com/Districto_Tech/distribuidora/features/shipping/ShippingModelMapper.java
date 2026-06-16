package com.Districto_Tech.distribuidora.features.shipping;

import com.Districto_Tech.distribuidora.common.IModelMapper;
import com.Districto_Tech.distribuidora.common.config.MapperConfig;
import com.Districto_Tech.distribuidora.features.shipping.dtos.ShippingRequestDTO;
import com.Districto_Tech.distribuidora.features.shipping.dtos.ShippingResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ShippingModelMapper implements IModelMapper<ShippingEntity, ShippingResponseDTO, ShippingRequestDTO>{

    private MapperConfig mapperConfig;

    @Override
    public ShippingEntity toEntity(ShippingRequestDTO shippingRequestDto) {
        return mapperConfig.modelMapper().map(shippingRequestDto, ShippingEntity.class);
    }

    @Override
    public ShippingResponseDTO toDto(ShippingEntity shippingEntity) {
        return mapperConfig.modelMapper().map(shippingEntity, ShippingResponseDTO.class);

    }

}
