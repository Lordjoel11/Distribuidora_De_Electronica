package com.Districto_Tech.distribuidora.features.orders_details;

import com.Districto_Tech.distribuidora.features.orders_details.dto.OrderDetailsRequestDto;
import com.Districto_Tech.distribuidora.features.orders_details.dto.OrderDetailsResponseDto;
import com.Districto_Tech.distribuidora.features.products.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderDetailsServiceTest {

    @Mock
    private OrderDetailsRepository orderDetailsRepository;

    @Mock
    private OrderDetailsModelMapper orderDetailsMapper;

    @InjectMocks
    private OrderDetailsService orderDetailsService;

    private OrderDetails orderDetails;
    private OrderDetailsRequestDto requestDto;
    private OrderDetailsResponseDto responseDto;
    private UUID publicId;

    @BeforeEach
    void setUp() {
        publicId = UUID.randomUUID();

        Product product = Product.builder().id(1L).name("Monitor").unitPrice(300.0).build();

        orderDetails = OrderDetails.builder()
                .id(1L)
                .publicId(publicId)
                .orderDescription("Monitor 27 pulgadas")
                .orderQuantity(3)
                .historicalPrice(300.0)
                .productEntity(product)
                .build();

        requestDto = OrderDetailsRequestDto.builder()
                .productId(1L)
                .orderDescription("Monitor 27 pulgadas")
                .orderQuantity(3)
                .build();

        responseDto = OrderDetailsResponseDto.builder()
                .description("Monitor 27 pulgadas")
                .quantity(3)
                .historicalPrice(300.0)
                .build();
    }

    @Test
    void createOrder_ShouldCreateOrderDetailSuccessfully() {
        when(orderDetailsMapper.toEntity(any(OrderDetailsRequestDto.class))).thenReturn(orderDetails);
        when(orderDetailsRepository.save(any(OrderDetails.class))).thenReturn(orderDetails);
        when(orderDetailsMapper.toDto(any(OrderDetails.class))).thenReturn(responseDto);

        OrderDetailsResponseDto result = orderDetailsService.createOrder(requestDto);

        assertNotNull(result);
        assertEquals("Monitor 27 pulgadas", result.getDescription());
        assertEquals(3, result.getQuantity());
        verify(orderDetailsRepository).save(any());
    }

    @Test
    void listOrders_ShouldReturnAllOrderDetails() {
        when(orderDetailsRepository.findAll()).thenReturn(List.of(orderDetails));
        when(orderDetailsMapper.toDto(any(OrderDetails.class))).thenReturn(responseDto);

        List<OrderDetailsResponseDto> result = orderDetailsService.listOrders(null);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void listOrders_ByPublicId_ShouldReturnFilteredDetails() {
        when(orderDetailsRepository.findByOrderEntity_OrderCode(publicId))
                .thenReturn(List.of(orderDetails));
        when(orderDetailsMapper.toDto(any(OrderDetails.class))).thenReturn(responseDto);

        List<OrderDetailsResponseDto> result = orderDetailsService.listOrders(publicId);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void cancelOrderById_ShouldCancelOrder() {
        when(orderDetailsRepository.findById(1L)).thenReturn(Optional.of(orderDetails));
        when(orderDetailsMapper.toDto(any(OrderDetails.class))).thenReturn(responseDto);

        OrderDetailsResponseDto result = orderDetailsService.cancelOrderById(1L);

        assertNotNull(result);
        verify(orderDetailsRepository).save(any());
    }
}