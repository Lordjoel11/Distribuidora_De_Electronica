package com.Districto_Tech.distribuidora.features.orders_details;

import com.Districto_Tech.distribuidora.common.IMapper;
import com.Districto_Tech.distribuidora.features.orders.OrderEntity;
import com.Districto_Tech.distribuidora.features.orders_details.dto.OrderDetailsRequestDto;
import com.Districto_Tech.distribuidora.features.orders_details.dto.OrderDetailsResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
public class OrderDetailsMapper implements IMapper<OrderDetails, OrderDetailsResponseDto, OrderDetailsRequestDto> {
    ModelMapper modelMapper;

    @Override
    public OrderDetails toEntity(OrderDetailsRequestDto orderDetailsRequestDto) {
        return modelMapper.map(orderDetailsRequestDto, OrderDetails.class);
    }

    @Override
    public OrderDetailsResponseDto toDto(OrderDetails orderDetails) {
        return modelMapper.map(orderDetails, OrderDetailsResponseDto.class);
    }
}
