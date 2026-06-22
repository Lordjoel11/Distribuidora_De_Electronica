package com.Districto_Tech.distribuidora.features.orders;

import com.Districto_Tech.distribuidora.common.IModelMapper;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderRequestDto;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderResponseDto;
import com.Districto_Tech.distribuidora.features.orders_details.OrderDetailsModelMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderModelMapper implements IModelMapper<OrderEntity, OrderResponseDto, OrderRequestDto> {

    private final ModelMapper modelMapper;
    private final OrderDetailsModelMapper orderDetailsModelMapper;

    @Override
    public OrderEntity toEntity(OrderRequestDto dto) {
        return modelMapper.map(dto, OrderEntity.class);
    }

    @Override
    public OrderResponseDto toDto(OrderEntity entity) {
        OrderResponseDto dto = modelMapper.map(entity, OrderResponseDto.class);

        if (entity.getClient() != null) {
            dto.setClientId(entity.getClient().getId());
            dto.setClientName(entity.getClient().getNameAndSurname());
        }
        if (entity.getEmployee() != null) {
            dto.setEmployeeId(entity.getEmployee().getIdEmployee());
            dto.setEmployeeName(entity.getEmployee().getName());
        }
        if (entity.getOrderDetailsList() != null) {
            dto.setItems(entity.getOrderDetailsList().stream()
                    .map(orderDetailsModelMapper::toDto).toList());

            double total = entity.getOrderDetailsList().stream()
                    .mapToDouble(d -> d.getHistoricalPrice() * d.getQuantity())
                    .sum();
            dto.setTotal(total);
        }
        return dto;
    }
}