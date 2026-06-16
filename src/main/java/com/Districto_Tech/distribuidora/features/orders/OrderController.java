package com.Districto_Tech.distribuidora.features.orders;

import com.Districto_Tech.distribuidora.features.orders.dto.OrderRequestDto;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderResponseDto;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderStatusDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelOrderById(@PathVariable Long id) {

        orderService.cancelOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{order_code}")
    public ResponseEntity<Void> cancelOrderByCode(@PathVariable UUID orderCode) {

        orderService.cancelOrderByCode(orderCode);
        return ResponseEntity.status(HttpStatus.OK).build();
    }



    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> findAll(
            @RequestParam(required = false) Long clientId,
            @RequestParam (required = false) Status status) {
        return ResponseEntity.ok(orderService.findWithFilters(clientId, status));
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<OrderResponseDto> changeStatus(
            @PathVariable Long id,
            @Valid @RequestBody OrderStatusDto dto
    ) {
        return ResponseEntity.ok(orderService.changeStatus (id, dto));
    }
}
