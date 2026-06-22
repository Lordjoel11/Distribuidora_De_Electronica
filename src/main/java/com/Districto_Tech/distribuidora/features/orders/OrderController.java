package com.Districto_Tech.distribuidora.features.orders;

import com.Districto_Tech.distribuidora.features.orders.dto.OrderRequestDto;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderResponseDto;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderStatusDto;
import com.Districto_Tech.distribuidora.features.users.UserEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(
            @Valid @RequestBody OrderRequestDto dto,
            @AuthenticationPrincipal UserEntity currentUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(dto, currentUser.getId()));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> findAll(
            @RequestParam(required = false) Long clientId,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Boolean unassigned) {
        return ResponseEntity.ok(orderService.findWithFilters(clientId, status, unassigned));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getById(id));
    }

    @GetMapping("/code/{orderCode}")
    public ResponseEntity<OrderResponseDto> getByCode(@PathVariable UUID orderCode) {
        return ResponseEntity.ok(orderService.getByCode(orderCode));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponseDto> cancelOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.cancelOrderById(id));
    }

    @PatchMapping("/{id}/take")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<OrderResponseDto> takeOrder(
            @PathVariable Long id,
            @AuthenticationPrincipal UserEntity currentUser) {
        return ResponseEntity.ok(orderService.takeOrder(id, currentUser.getId()));
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<OrderResponseDto> changeStatus(
            @PathVariable Long id,
            @Valid @RequestBody OrderStatusDto dto) {
        return ResponseEntity.ok(orderService.changeStatus(id, dto));
    }
}