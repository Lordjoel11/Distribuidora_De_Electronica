package com.Districto_Tech.distribuidora.features.orders;

import com.Districto_Tech.distribuidora.common.exceptions.ResourceNotFoundException;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderRequestDto;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderResponseDto;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderStatusDto;
import com.Districto_Tech.distribuidora.features.orders_details.OrderDetails;
import com.Districto_Tech.distribuidora.features.orders_details.OrderDetailsRepository;
import com.Districto_Tech.distribuidora.features.orders_details.dto.OrderDetailsRequestDto;
import com.Districto_Tech.distribuidora.features.products.Product;
import com.Districto_Tech.distribuidora.features.products.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderModelMapper orderMapper;
    private final ProductRepository productRepository;

    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto dto) {

        OrderEntity savedOrder = orderRepository.save(orderMapper.toEntity(dto));

        for (OrderDetailsRequestDto itemDto : dto.getItems()) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado."));

            OrderDetails detail = OrderDetails.builder()
                    .order(savedOrder)
                    .product(product)
                    .quantity(itemDto.getQuantity())
                    .historicalPrice(product.getUnitPrice())
                    .build();

            orderDetailsRepository.save(detail);
        }

        return orderMapper.toDto(orderRepository.findById(savedOrder.getId()).orElseThrow());
    }

    public List<OrderResponseDto> findWithFilters(Long clientId, Status status) {
        if (clientId == null && status == null) {
            return orderRepository.findAll().stream().map(orderMapper::toDto).toList();
        }
        if (clientId == null) {
            return orderRepository.findByOrderStatus(status).stream().map(orderMapper::toDto).toList();
        }
        if (status == null) {
            return orderRepository.findByClient_Id(clientId).stream().map(orderMapper::toDto).toList();
        }
        return orderRepository.findByClient_IdAndOrderStatus(clientId, status).stream().map(orderMapper::toDto).toList();
    }

    public OrderResponseDto getById(Long id) {
        return orderMapper.toDto(orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado.")));
    }

    public OrderResponseDto getByCode(UUID orderCode) {
        return orderMapper.toDto(orderRepository.findByOrderCode(orderCode)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado.")));
    }

    @Transactional
    public OrderResponseDto cancelOrderById(Long id) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado."));

        validateTransaction(order.getOrderStatus(), Status.CANCELED);
        order.setOrderStatus(Status.CANCELED);
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Transactional
    public OrderResponseDto changeStatus(Long id, OrderStatusDto dto) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado."));

        validateTransaction(order.getOrderStatus(), dto.getNewStatus());

        if (dto.getNewStatus() == Status.CONFIRMED) {
            descontarStock(order);
        }

        order.setOrderStatus(dto.getNewStatus());
        return orderMapper.toDto(orderRepository.save(order));
    }

    private void validateTransaction(Status currentStatus, Status newStatus) {
        if (currentStatus == Status.CANCELED) {
            throw new IllegalStateException("No se puede modificar un pedido CANCELADO.");
        }
        if (currentStatus == Status.COMPLETED) {
            throw new IllegalStateException("No se puede modificar un pedido COMPLETADO.");
        }
        if (currentStatus == Status.COMPLETED && newStatus == Status.PENDING) {
            throw new IllegalStateException("No se puede volver a PENDIENTE desde COMPLETADO.");
        }
    }

    private void descontarStock(OrderEntity order) {
        for (OrderDetails detail : order.getOrderDetailsList()) {
            Product product = detail.getProduct();
            int stockActual = product.getStock();
            int cantidad = detail.getQuantity();

            if (stockActual < cantidad) {
                throw new IllegalStateException("Stock insuficiente para el producto: " + product.getName());
            }
            product.setStock(stockActual - cantidad);
            productRepository.save(product);
        }
    }
}