package com.Districto_Tech.distribuidora.features.orders.dto;

import com.Districto_Tech.distribuidora.common.IMapper;
import com.Districto_Tech.distribuidora.features.orders.OrderEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class OrderMapper implements IMapper<OrderEntity, OrderResponseDto, OrderRequestDto> {
    ModelMapper modelMapper;

    public OrderMapper(ModelMapper modelMapper) {
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
