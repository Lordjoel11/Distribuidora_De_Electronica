package com.Districto_Tech.distribuidora.features.orders;

import com.Districto_Tech.distribuidora.common.IModelMapper;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderRequestDto;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderResponseDto;
import com.Districto_Tech.distribuidora.features.orders_details.OrderDetailsModelMapper;
import com.Districto_Tech.distribuidora.features.orders_details.OrderDetailsModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderModelMapper implements IModelMapper<OrderEntity, OrderResponseDto, OrderRequestDto> {

    private final ModelMapper modelMapper;
    private final OrderDetailsModelMapper orderDetailsModelMapper;

    public OrderModelMapper(ModelMapper modelMapper, OrderDetailsModelMapper orderDetailsModelMapper) {
        this.modelMapper = modelMapper;
        this.orderDetailsModelMapper = orderDetailsModelMapper;
    }

    @Override
    public OrderEntity toEntity(OrderRequestDto orderRequestDto) {
        return modelMapper.map(orderRequestDto, OrderEntity.class);
    }

    @Override
    public OrderResponseDto toDto(OrderEntity orderEntity) {
        OrderResponseDto dto = modelMapper.map(orderEntity, OrderResponseDto.class);
        if (orderEntity.getOrderDetailsList() != null) {
            dto.setOrderDetails(
                    orderEntity.getOrderDetailsList().stream()
                            .map(orderDetailsModelMapper::toDto)
                            .toList()
            );
        }
        return dto;
    }
}