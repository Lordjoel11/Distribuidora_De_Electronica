package com.Districto_Tech.distribuidora.features.orders;

import com.Districto_Tech.distribuidora.features.clients.ClientEntity;
import com.Districto_Tech.distribuidora.features.clients.ClientRepository;
import com.Districto_Tech.distribuidora.features.employees.EmployeeEntity;
import com.Districto_Tech.distribuidora.features.employees.EmployeeRepository;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderRequestDto;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderResponseDto;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderStatusDto;
import com.Districto_Tech.distribuidora.features.orders_details.OrderDetailsRepository;
import com.Districto_Tech.distribuidora.features.orders_details.dto.OrderDetailsRequestDto;
import com.Districto_Tech.distribuidora.features.products.Product;
import com.Districto_Tech.distribuidora.features.products.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderModelMapper orderMapper;
    @Mock
    private OrderDetailsRepository orderDetailsRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private OrderService orderService;

    private ClientEntity client;
    private EmployeeEntity employee;
    private Product product;
    private OrderEntity orderEntity;
    private OrderRequestDto orderRequestDto;
    private OrderResponseDto orderResponseDto;

    @BeforeEach
    void setUp() {
        client = ClientEntity.builder().id(1L).nameAndSurname("Juan Perez").build();
        employee = EmployeeEntity.builder().idEmployee(1L).name("Maria").build();
        product = Product.builder().id(1L).name("Laptop").stock(10).unitPrice(1000.0).build();

        OrderDetailsRequestDto detailDto = OrderDetailsRequestDto.builder()
                .productId(1L)
                .orderDescription("Laptop gaming")
                .orderQuantity(2)
                .build();

        orderRequestDto = OrderRequestDto.builder()
                .clientId(1L)
                .employeeId(1L)
                .orderDetails(List.of(detailDto))
                .build();

        orderEntity = OrderEntity.builder()
                .id(1L)
                .orderCode(java.util.UUID.randomUUID())
                .orderDate(LocalDate.now())
                .orderStatus(Status.PENDING)
                .clientId(client)
                .employeeId(employee)
                .build();

        orderResponseDto = OrderResponseDto.builder()
                .id(1L)
                .orderStatus(Status.PENDING)
                .build();
    }

    @Test
    void createOrder_ShouldCreateSuccessfully_WhenStockIsEnough() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);
        when(orderMapper.toDto(any(OrderEntity.class))).thenReturn(orderResponseDto);

        OrderResponseDto result = orderService.createOrder(orderRequestDto);

        assertNotNull(result);
        assertEquals(Status.PENDING, orderEntity.getOrderStatus());
        verify(productRepository).save(any(Product.class)); // Verifica que descontó stock
        assertEquals(8, product.getStock()); // 10 - 2
    }

    @Test
    void createOrder_ShouldThrowException_WhenStockIsInsufficient() {
        product.setStock(1); // Menos stock del solicitado

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        assertThrows(IllegalArgumentException.class, () -> orderService.createOrder(orderRequestDto));
    }

    @Test
    void cancelOrderById_ShouldCancelOrder() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(orderEntity));
        when(orderMapper.toDto(any(OrderEntity.class))).thenReturn(orderResponseDto);

        OrderResponseDto result = orderService.cancelOrderById(1L);

        assertNotNull(result);
        assertEquals(Status.CANCELED, orderEntity.getOrderStatus());
        verify(orderRepository).save(orderEntity);
    }

    @Test
    void changeStatus_WhenConfirmed_ShouldDiscountStock() {
        OrderStatusDto statusDto = new OrderStatusDto();
        statusDto.setNewStatus(Status.CONFIRMED);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(orderEntity));
        when(orderMapper.toDto(any())).thenReturn(orderResponseDto);

        orderService.changeStatus(1L, statusDto);

        verify(productRepository).save(any(Product.class));
    }
}