package com.Districto_Tech.distribuidora.features.orders;

import com.Districto_Tech.distribuidora.common.exceptions.ResourceNotFoundException;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderRequestDto;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderResponseDto;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderStatusDto;
import com.Districto_Tech.distribuidora.features.orders_details.OrderDetails;
import com.Districto_Tech.distribuidora.features.orders_details.OrderDetailsModelMapper;
import com.Districto_Tech.distribuidora.features.orders_details.OrderDetailsModelMapper;
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
    private final OrderDetailsModelMapper orderDetailsModelMapper;
    private final ProductRepository productRepository;

    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderStatus(Status.PENDING);
        orderEntity.setOrderDate(LocalDate.now());
        OrderEntity savedOrder = orderRepository.save(orderEntity);


        for (OrderDetailsRequestDto detailDto : orderRequestDto.getOrderDetails()) {
            Product product = productRepository.findById(detailDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found."));

            if (product.getStock() < detailDto.getOrderQuantity()) {
                throw new IllegalArgumentException("Insufficient stock for product: " + product.getName()
                        + ". Available: " + product.getStock()
                        + ", Requested: " + detailDto.getOrderQuantity());
            }

            product.setStock(product.getStock() - detailDto.getOrderQuantity());
            productRepository.save(product);

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

    public List<OrderResponseDto> findWithFilters(Long clientId, Status status) {
        if (clientId == null && status == null)
            return orderRepository.findAll().stream().map(orderMapper::toDto).toList();

        if (clientId == null)
            return orderRepository.findByOrderStatus(status).stream().map(orderMapper::toDto).toList();

        if (status == null)
            return orderRepository.findByClientId(clientId).stream().map(orderMapper::toDto).toList();

        return orderRepository.findByClient_IdAndOrderStatus(clientId, status).stream().map(orderMapper::toDto).toList();
    }

    private void validateTransaction(Status currentStatus, Status newStatus) {

        if (currentStatus == Status.COMPLETED && newStatus == Status.PENDING) {
            throw new IllegalStateException("No se puede volver a PENDIENTE desde ENTREGADO.");
        }

        if (currentStatus == Status.CANCELED) {
            throw new IllegalStateException("No se puede modificar un pedido CANCELADO.");
        }

        if (currentStatus == Status.COMPLETED) {
            throw new IllegalStateException("No se puede modificar un pedido COMPLETADO.");
        }
    }

    private void descontarStock(OrderEntity order) {
        for (OrderDetails detalle : order.getOrderDetailsList()) {
            Product producto = detalle.getProductEntity();
            int stockActual = producto.getStock();
            int cantidad = detalle.getOrderQuantity();

            if (stockActual < cantidad) {
                throw new IllegalStateException(
                        "Stock insuficiente para el producto: " + producto.getName()
                );
            }
            producto.setStock(stockActual - cantidad);
            productRepository.save(producto);
        }
    }

    public OrderResponseDto changeStatus(Long id, OrderStatusDto dto) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido no encontrado: " + id));


        validateTransaction(order.getOrderStatus(), dto.getNewStatus());


        if (dto.getNewStatus() == Status.CONFIRMED) {
            descontarStock(order);
        }

        order.setOrderStatus(dto.getNewStatus());
        return orderMapper.toDto(orderRepository.save(order));
    }
}