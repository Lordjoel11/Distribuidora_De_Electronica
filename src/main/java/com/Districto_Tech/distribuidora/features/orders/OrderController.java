package com.Districto_Tech.distribuidora.features.orders;

import com.Districto_Tech.distribuidora.features.orders.dto.OrderRequestDto;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderRequestDto));

    }

    @DeleteMapping("/api/{id}")
    public ResponseEntity<Void> cancelOrderById(@PathVariable Long id) {

        orderService.cancelOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/api/{order_code}")
    public ResponseEntity<Void> cancelOrderByCode(@PathVariable UUID orderCode) {

        orderService.cancelOrderByCode(orderCode);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
