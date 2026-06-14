package com.Districto_Tech.distribuidora.features.orders_details;

import com.Districto_Tech.distribuidora.features.orders.Status;
import com.Districto_Tech.distribuidora.features.orders_details.dto.OrderDetailsRequestDto;
import com.Districto_Tech.distribuidora.features.orders_details.dto.OrderDetailsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class OrderDetailsService {


    private final OrderDetailsModelMapper orderDetailsMapper;
    private final OrderDetailsRepository orderDetailsRepository;


    public OrderDetailsResponseDto createOrder(OrderDetailsRequestDto orderDetailsRequestDto) {

        OrderDetails orderDetails = orderDetailsRepository.findFirstByOrderEntity_OrderCode(orderDetailsRequestDto.getPublicId()).
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

        OrderDetails orderDetails = orderDetailsRepository.findFirstByOrderEntity_OrderCode(publicId).
                orElseThrow(() -> new NoSuchElementException("No order with this code was found."));

        orderDetails.getOrderEntity().setOrderStatus(Status.CANCELED);
        orderDetailsRepository.save(orderDetails);
        return orderDetailsMapper.toDto(orderDetails);
    }

    public List<OrderDetailsResponseDto> listOrders(UUID publicId) {

        if (publicId != null) {
            return orderDetailsRepository.findByOrderEntity_OrderCode(publicId).stream()
                    .map(orderDetailsMapper::toDto).toList();
        }

        return orderDetailsRepository.findAll().stream()
                .map(orderDetailsMapper::toDto)
                .toList();
    }


}