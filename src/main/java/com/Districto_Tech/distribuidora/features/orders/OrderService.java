package com.Districto_Tech.distribuidora.features.orders;


import com.Districto_Tech.distribuidora.features.orders.dto.OrderRequestDto;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class OrderService {


    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;


    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {

        OrderEntity ordeer = orderRepository.findByOrderCode(orderRequestDto.getOrderCode()).
                orElseThrow(() -> new NoSuchElementException("The requested order code was not found."));


        OrderEntity orderEntity = orderMapper.toEntity(orderRequestDto);

        orderEntity.setOrderStatus(Status.PENDING);
        orderEntity.setOrderDate(LocalDate.now());


        return orderMapper.toDto(orderRepository.save(orderEntity));
    }

    public OrderResponseDto cancelOrderById(Long id) {

        OrderEntity orderEntity = orderRepository.findById(id).
                orElseThrow(() -> new NoSuchElementException("No order with this ID was found."));

        orderEntity.setOrderStatus(Status.CANCELED);
        orderRepository.save(orderEntity);
        return orderMapper.toDto(orderEntity);
    }

    public OrderResponseDto cancelOrderByCode(UUID orderCode) {

        OrderEntity orderEntity = orderRepository.findByOrderCode(orderCode).
                orElseThrow(() -> new NoSuchElementException("No order with this code was found."));

        orderEntity.setOrderStatus(Status.CANCELED);
        orderRepository.save(orderEntity);
        return orderMapper.toDto(orderEntity);
    }

}
