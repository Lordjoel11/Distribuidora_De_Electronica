package com.Districto_Tech.distribuidora.features.orders;

import com.Districto_Tech.distribuidora.common.exceptions.AccessDeniedException;
import com.Districto_Tech.distribuidora.common.exceptions.InvalidDataException;
import com.Districto_Tech.distribuidora.common.exceptions.ResourceNotFoundException;
import com.Districto_Tech.distribuidora.features.clients.ClientEntity;
import com.Districto_Tech.distribuidora.features.clients.ClientRepository;
import com.Districto_Tech.distribuidora.features.employees.EmployeeEntity;
import com.Districto_Tech.distribuidora.features.employees.EmployeeRepository;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderRequestDto;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderResponseDto;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderStatusDto;
import com.Districto_Tech.distribuidora.features.orders_details.OrderDetails;
import com.Districto_Tech.distribuidora.features.orders_details.OrderDetailsRepository;
import com.Districto_Tech.distribuidora.features.orders_details.dto.OrderDetailsRequestDto;
import com.Districto_Tech.distribuidora.features.payments.PaymentRepository;
import com.Districto_Tech.distribuidora.features.products.Product;
import com.Districto_Tech.distribuidora.features.products.ProductRepository;
import com.Districto_Tech.distribuidora.features.users.ApprovalStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderModelMapper orderMapper;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;
    private final PaymentRepository paymentRepository;

    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto dto, Long currentUserId) {

        ClientEntity client = clientRepository.findByUser_Id(currentUserId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un perfil de cliente para este usuario."));

        if(client.getUser().getApprovalStatus().equals(ApprovalStatus.SUSPENDED))
            throw new AccessDeniedException("Su cuenta esta suspendida. No puedes accionar.");
        if(client.getUser().getApprovalStatus().equals(ApprovalStatus.PENDING))
            throw new AccessDeniedException("Su cuenta esta pendiente. No puedes accionar.");

        OrderEntity order = OrderEntity.builder()
                .orderStatus(Status.PENDING)
                .orderDate(LocalDate.now())
                .client(client)
                .employee(null)
                .build();

        OrderEntity savedOrder = orderRepository.save(order);

        List<OrderDetails> createdDetails = new ArrayList<>();

        for (OrderDetailsRequestDto itemDto : dto.getItems()) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado."));

            OrderDetails detail = OrderDetails.builder()
                    .order(savedOrder)
                    .product(product)
                    .quantity(itemDto.getQuantity())
                    .historicalPrice(product.getUnitPrice())
                    .build();

            createdDetails.add(orderDetailsRepository.save(detail));
        }

        savedOrder.setOrderDetailsList(createdDetails);

        return orderMapper.toDto(savedOrder);
    }

    @Transactional
    public OrderResponseDto takeOrder(Long orderId, Long currentUserId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado."));

        if (order.getEmployee() != null) {
            throw new AccessDeniedException("Este pedido ya fue tomado por otro empleado.");
        }

        EmployeeEntity employee = employeeRepository.findByUser_Id(currentUserId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un perfil de empleado para este usuario."));

        order.setEmployee(employee);
        return orderMapper.toDto(orderRepository.save(order));
    }

    public List<OrderResponseDto> findWithFilters(Long clientId, Status status, Boolean unassigned) {
        List<OrderEntity> result;

        if (Boolean.TRUE.equals(unassigned)) {
            result = orderRepository.findByEmployeeIsNull();
        } else if (clientId == null && status == null) {
            result = orderRepository.findAll();
        } else if (clientId == null) {
            result = orderRepository.findByOrderStatus(status);
        } else {
            ClientEntity client = clientRepository.findById(clientId)
                    .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado."));
            result = (status == null)
                    ? orderRepository.findByClient(client)
                    : orderRepository.findByClientAndOrderStatus(client, status);
        }

        return result.stream().map(orderMapper::toDto).toList();
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

        if(order.getOrderStatus() == Status.CONFIRMED){
            devolverStock(order);
            order.setStockDiscounted(true);
        }

        if(order.isStockDiscounted()){
            devolverStock(order);
            order.setStockDiscounted(false);
        }

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

        if (dto.getNewStatus() == Status.COMPLETED) {
            boolean tienePago = !paymentRepository.findByOrder_Id(order.getId()).isEmpty();
            if (!tienePago) {
                throw new InvalidDataException("No se puede completar el pedido sin un pago registrado.");
            }
        }

        order.setOrderStatus(dto.getNewStatus());
        return orderMapper.toDto(orderRepository.save(order));
    }

    private void validateTransaction(Status currentStatus, Status newStatus) {
        if (currentStatus == Status.CANCELED) {
            throw new AccessDeniedException("No se puede modificar un pedido CANCELADO.");
        }
        if (currentStatus == Status.COMPLETED) {
            throw new AccessDeniedException("No se puede modificar un pedido COMPLETADO.");
        }
        if (currentStatus == Status.COMPLETED && newStatus == Status.PENDING) {
            throw new AccessDeniedException("No se puede volver a PENDIENTE desde COMPLETADO.");
        }
    }

    private void descontarStock(OrderEntity order) {
        for (OrderDetails detail : order.getOrderDetailsList()) {
            Product product = detail.getProduct();
            int stockActual = product.getStock();
            int cantidad = detail.getQuantity();

            if (stockActual < cantidad) {
                throw new InvalidDataException("Stock insuficiente para el producto: " + product.getName());
            }
            product.setStock(stockActual - cantidad);
            productRepository.save(product);
        }
    }

    private void devolverStock(OrderEntity order) {
        for (OrderDetails detail : order.getOrderDetailsList()) {

            Product product = detail.getProduct();

            product.setStock(
                    product.getStock() + detail.getQuantity()
            );

            productRepository.save(product);
        }
    }

    public void deleteById(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. El Recurso no existe.");
        }
        orderRepository.deleteById(id);
    }
}