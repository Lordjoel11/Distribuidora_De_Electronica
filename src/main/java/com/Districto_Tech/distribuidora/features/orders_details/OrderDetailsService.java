package com.Districto_Tech.distribuidora.features.orders_details;

import com.Districto_Tech.distribuidora.features.orders.OrderEntity;
import com.Districto_Tech.distribuidora.features.orders.OrderMapper;
import com.Districto_Tech.distribuidora.features.orders.OrderRepository;
import com.Districto_Tech.distribuidora.features.orders.Status;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderRequestDto;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderResponseDto;
import com.Districto_Tech.distribuidora.features.orders_details.dto.OrderDetailsRequestDto;
import com.Districto_Tech.distribuidora.features.orders_details.dto.OrderDetailsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class OrderDetailsService {


    private final OrderDetailsMapper orderDetailsMapper;
    private final OrderDetailsRepository orderDetailsRepository;


    public OrderDetailsResponseDto createOrder(OrderDetailsRequestDto orderDetailsRequestDto) {

        OrderDetails orderDetails = orderDetailsRepository.findByOrderCode(orderDetailsRequestDto.getPublicId()).
                orElseThrow(() -> new NoSuchElementException("The requested order code was not found."));


        orderDetails = orderDetailsMapper.toEntity(orderDetailsRequestDto);

        orderDetails.setHistoricalPrice(orderDetails.getProductEntity().getUnitPrice());


        return orderDetailsMapper.toDto(orderDetailsRepository.save(orderDetails));
    }

    public OrderDetailsResponseDto cancelOrderById(Long id) {

        OrderDetails  orderDetails = orderDetailsRepository.findById(id).
                orElseThrow(() -> new NoSuchElementException("No order with this ID was found."));

        orderDetails.getOrderEntity().setOrderStatus(Status.CANCELED);
        orderDetailsRepository.save(orderDetails);
        return orderDetailsMapper.toDto(orderDetails);
    }

    public OrderDetailsResponseDto cancelOrderByCode(UUID publicId) {

        OrderDetails orderDetails = orderDetailsRepository.findByOrderCode(publicId).
                orElseThrow(() -> new NoSuchElementException("No order with this code was found."));

        orderDetails.getOrderEntity().setOrderStatus(Status.CANCELED);
        orderDetailsRepository.save(orderDetails);
        return orderDetailsMapper.toDto(orderDetails);
    }

    public List<OrderDetailsResponseDto> listOrders(UUID publicId) {

        if (publicId != null) {
            return orderDetailsRepository.getByOrderCode(publicId).stream()
                    .map(orderDetailsMapper::toDto).toList();
        }

        return orderDetailsRepository.findAll().stream()
                .map(orderDetailsMapper::toDto)
                .toList();
    }


}