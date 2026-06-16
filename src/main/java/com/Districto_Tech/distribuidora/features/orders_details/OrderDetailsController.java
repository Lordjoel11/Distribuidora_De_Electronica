package com.Districto_Tech.distribuidora.features.orders_details;

import com.Districto_Tech.distribuidora.features.orders.OrderService;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderRequestDto;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderResponseDto;
import com.Districto_Tech.distribuidora.features.orders_details.dto.OrderDetailsRequestDto;
import com.Districto_Tech.distribuidora.features.orders_details.dto.OrderDetailsResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order-details")
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;

    @PostMapping
    public ResponseEntity<OrderDetailsResponseDto> createOrder(@Valid @RequestBody OrderDetailsRequestDto orderDetailsRequestDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(orderDetailsService.createOrder(orderDetailsRequestDto));

    }

    @DeleteMapping("/api/order_details/{id}")
    public ResponseEntity<Void> cancelOrderById(@PathVariable Long id) {

        orderDetailsService.cancelOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/api/order_details/{order_code}")
    public ResponseEntity<Void> cancelOrderByCode(@PathVariable UUID publicId) {

        orderDetailsService.cancelOrderByCode(publicId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
