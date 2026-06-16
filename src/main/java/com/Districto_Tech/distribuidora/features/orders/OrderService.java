package com.Districto_Tech.distribuidora.features.orders;

import com.Districto_Tech.distribuidora.common.exceptions.ResourceNotFoundException;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderRequestDto;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderResponseDto;
import com.Districto_Tech.distribuidora.features.orders_details.OrderDetails;
import com.Districto_Tech.distribuidora.features.orders_details.OrderDetailsMapper;
import com.Districto_Tech.distribuidora.features.orders_details.OrderDetailsRepository;
import com.Districto_Tech.distribuidora.features.orders_details.dto.OrderDetailsRequestDto;
import com.Districto_Tech.distribuidora.features.products.Product;
import com.Districto_Tech.distribuidora.features.products.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderModelMapper orderMapper;
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderDetailsMapper orderDetailsMapper;
    private final ProductRepository productRepository;

    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        // Crear la orden
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderStatus(Status.PENDING);
        orderEntity.setOrderDate(LocalDate.now());
        OrderEntity savedOrder = orderRepository.save(orderEntity);

        // Crear cada detalle asociado a la orden
        for (OrderDetailsRequestDto detailDto : orderRequestDto.getOrderDetails()) {
            Product product = productRepository.findById(detailDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found."));

            OrderDetails detail = new OrderDetails();
            detail.setOrderEntity(savedOrder);
            detail.setProductEntity(product);
            detail.setOrderDescription(detailDto.getOrderDescription());
            detail.setOrderQuantity(detailDto.getOrderQuantity());
            detail.setHistoricalPrice(product.getUnitPrice());
            orderDetailsRepository.save(detail);
        }

        return orderMapper.toDto(orderRepository.findById(savedOrder.getId()).orElseThrow());
    }

    public OrderResponseDto cancelOrderById(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No order with this ID was found."));
        orderEntity.setOrderStatus(Status.CANCELED);
        orderRepository.save(orderEntity);
        return orderMapper.toDto(orderEntity);
    }

    public OrderResponseDto cancelOrderByCode(UUID orderCode) {
        OrderEntity orderEntity = orderRepository.findByOrderCode(orderCode)
                .orElseThrow(() -> new NoSuchElementException("No order with this code was found."));
        orderEntity.setOrderStatus(Status.CANCELED);
        orderRepository.save(orderEntity);
        return orderMapper.toDto(orderEntity);
    }

    public List<OrderResponseDto> listOrders(UUID orderCode) {
        if (orderCode != null) {
            return orderRepository.getByOrderCode(orderCode).stream()
                    .map(orderMapper::toDto).toList();
        }
        return orderRepository.findAll().stream()
                .map(orderMapper::toDto).toList();
    }
}