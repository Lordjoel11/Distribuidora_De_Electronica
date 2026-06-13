package com.Districto_Tech.distribuidora.features.orders;


import com.Districto_Tech.distribuidora.features.orders.dto.OrderRequestDto;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor

public class OrderService {


private final OrderMapper orderMapper;
private final OrderRepository orderRepository;


public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {

OrderEntity ordeer = orderRepository.findByOrderCode(orderRequestDto.getOrderCode()).
        orElseThrow(()-> new NoSuchElementException("The requested order code was not found."));


OrderEntity orderEntity = orderMapper.toEntity(orderRequestDto);

orderEntity.setOrderStatus(Status.PENDING);



return orderMapper.toDto(orderEntity);
}
}
