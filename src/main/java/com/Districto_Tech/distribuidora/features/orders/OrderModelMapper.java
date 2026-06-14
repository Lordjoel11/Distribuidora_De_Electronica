package com.Districto_Tech.distribuidora.features.orders;

import com.Districto_Tech.distribuidora.common.IModelMapper;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderRequestDto;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class OrderModelMapper implements IModelMapper<OrderEntity, OrderResponseDto, OrderRequestDto> {
    ModelMapper modelMapper;

    public OrderModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderEntity toEntity(OrderRequestDto orderRequestDto) {
        return modelMapper.map(orderRequestDto, OrderEntity.class);
    }

    @Override
    public OrderResponseDto toDto(OrderEntity orderEntity) {
        return modelMapper.map(orderEntity, OrderResponseDto.class);
    }
}
