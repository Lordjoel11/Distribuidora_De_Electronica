package com.Districto_Tech.distribuidora.features.orders_details;

import com.Districto_Tech.distribuidora.features.orders_details.dto.OrderDetailsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
@RequiredArgsConstructor
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;

    @GetMapping
    public ResponseEntity<List<OrderDetailsResponseDto>> getAll() {
        return ResponseEntity.ok(orderDetailsService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailsResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderDetailsService.getById(id));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderDetailsResponseDto>> getByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderDetailsService.getByOrderId(orderId));
    }
}