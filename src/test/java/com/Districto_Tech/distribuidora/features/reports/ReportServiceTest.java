package com.Districto_Tech.distribuidora.features.reports;

import com.Districto_Tech.distribuidora.features.orders.OrderEntity;
import com.Districto_Tech.distribuidora.features.orders.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private ReportService reportService;

    private OrderEntity order1;
    private OrderEntity order2;
    private OrderDetails detail1;
    private OrderDetails detail2;

    @BeforeEach
    void setUp() {
        detail1 = OrderDetails.builder()
                .historicalPrice(1000.0)
                .orderQuantity(2)
                .build();

        detail2 = OrderDetails.builder()
                .historicalPrice(500.0)
                .orderQuantity(3)
                .build();

        order1 = OrderEntity.builder()
                .id(1L)
                .orderDetailsList(List.of(detail1))
                .build();

        order2 = OrderEntity.builder()
                .id(2L)
                .orderDetailsList(List.of(detail2))
                .build();
    }

    @Test
    void getVentasPorPeriodo_ShouldCalculateCorrectTotalAndCount() {
        LocalDate start = LocalDate.of(2025, 6, 1);
        LocalDate end = LocalDate.of(2025, 6, 30);

        when(orderRepository.findByFechaInicioBetween(start, end))
                .thenReturn(List.of(order1, order2));

        SalesReportsDto result = reportService.getVentasPorPeriodo(start, end);

        assertNotNull(result);
        assertEquals(start, result.getFechaInicio());
        assertEquals(end, result.getFechaFin());
        assertEquals(2, result.getCantidadPedidos());
        assertEquals(3500.0, result.getMontoTotal());
    }

    @Test
    void getVentasPorPeriodo_WhenNoOrders_ShouldReturnZeroValues() {
        LocalDate start = LocalDate.of(2025, 1, 1);
        LocalDate end = LocalDate.of(2025, 1, 31);

        when(orderRepository.findByFechaInicioBetween(start, end))
                .thenReturn(List.of());

        SalesReportsDto result = reportService.getVentasPorPeriodo(start, end);

        assertNotNull(result);
        assertEquals(0, result.getCantidadPedidos());
        assertEquals(0.0, result.getMontoTotal());
    }
}