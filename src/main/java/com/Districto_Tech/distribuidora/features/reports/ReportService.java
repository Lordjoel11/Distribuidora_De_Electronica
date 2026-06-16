package com.Districto_Tech.distribuidora.features.reports;

import com.Districto_Tech.distribuidora.features.orders.OrderRepository;
import com.Districto_Tech.distribuidora.features.orders.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final OrderRepository orderRepository;

    public SalesReportsDto getVentasPorPeriodo(LocalDate startDate, LocalDate endDate) {

        List<OrderEntity> order = orderRepository.findByFechaInicioBetween(startDate, endDate);

        double montoTotal = order.stream()
                .flatMap(o -> o.getOrderDetailsList().stream())
                .mapToDouble(d -> d.getHistoricalPrice() * d.getOrderQuantity())
                .sum();

        return SalesReportsDto.builder()
                .fechaInicio(startDate)
                .fechaFin(endDate)
                .cantidadPedidos(order.size())
                .montoTotal(montoTotal)
                .build();
    }
}