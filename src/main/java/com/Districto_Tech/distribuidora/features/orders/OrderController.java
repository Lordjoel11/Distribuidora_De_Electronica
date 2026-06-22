package com.Districto_Tech.distribuidora.features.orders;

import com.Districto_Tech.distribuidora.features.orders.dto.OrderRequestDto;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderResponseDto;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderStatusDto;
import com.Districto_Tech.distribuidora.features.users.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Orders", description = "Gestión de pedidos de clientes")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Crear nuevo pedido", description = "Cliente crea un pedido con sus items")
    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(
            @Valid @RequestBody OrderRequestDto dto,
            @AuthenticationPrincipal UserEntity currentUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(dto, currentUser.getId()));
    }

    @Operation(summary = "Listar pedidos con filtros")
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> findAll(
            @RequestParam(required = false) Long clientId,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Boolean unassigned) {
        return ResponseEntity.ok(orderService.findWithFilters(clientId, status, unassigned));
    }

    @Operation(summary = "Obtener por ID")
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getById(id));
    }

    @Operation(summary = "Obtener por UUID")
    @GetMapping("/code/{orderCode}")
    public ResponseEntity<OrderResponseDto> getByCode(@PathVariable UUID orderCode) {
        return ResponseEntity.ok(orderService.getByCode(orderCode));
    }

    @Operation(summary = "Cancelar pedido")
    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponseDto> cancelOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.cancelOrderById(id));
    }

    @Operation(summary = "Tomar pedido (Empleado/Admin)")
    @PatchMapping("/{id}/take")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<OrderResponseDto> takeOrder(
            @PathVariable Long id,
            @AuthenticationPrincipal UserEntity currentUser) {
        return ResponseEntity.ok(orderService.takeOrder(id, currentUser.getId()));
    }

    @Operation(summary = "Cambiar estado del pedido")
    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<OrderResponseDto> changeStatus(
            @PathVariable Long id,
            @Valid @RequestBody OrderStatusDto dto) {
        return ResponseEntity.ok(orderService.changeStatus(id, dto));
    }
}