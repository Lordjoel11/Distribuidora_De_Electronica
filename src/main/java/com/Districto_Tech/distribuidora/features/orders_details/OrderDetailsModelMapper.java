package com.Districto_Tech.distribuidora.features.orders_details;

import com.Districto_Tech.distribuidora.common.IModelMapper;
import com.Districto_Tech.distribuidora.features.orders_details.dto.OrderDetailsRequestDto;
import com.Districto_Tech.distribuidora.features.orders_details.dto.OrderDetailsResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderDetailsModelMapper implements IModelMapper<OrderDetails, OrderDetailsResponseDto, OrderDetailsRequestDto> {

    private final ModelMapper modelMapper;

    @Override
    public OrderDetails toEntity(OrderDetailsRequestDto dto) {
        return modelMapper.map(dto, OrderDetails.class);
    }

    @Override
    public OrderDetailsResponseDto toDto(OrderDetails entity) {
        OrderDetailsResponseDto dto = modelMapper.map(entity, OrderDetailsResponseDto.class);
        if (entity.getProduct() != null) {              // antes: getProductEntity()
            dto.setProductId(entity.getProduct().getId());
            dto.setProductName(entity.getProduct().getName());
        }
        if (entity.getOrder() != null) {                  // antes: getOrderEntity()
            dto.setOrderId(entity.getOrder().getId());
        }
        return dto;
    }
}