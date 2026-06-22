package com.Districto_Tech.distribuidora.features.payments;

import com.Districto_Tech.distribuidora.features.payments.dto.PaymentRequestDto;
import com.Districto_Tech.distribuidora.features.payments.dto.PaymentResponseDto;
import com.Districto_Tech.distribuidora.features.users.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Payments", description = "Gestión de pagos de pedidos")
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "Listar todos los pagos")
    @GetMapping
    public ResponseEntity<List<PaymentResponseDto>> getAll() {
        return ResponseEntity.ok(paymentService.getAll());
    }

    @Operation(summary = "Obtener pago")
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getById(id));
    }


    @Operation(summary = "Registrar pago", description = "Registra un pago para un pedido")
    @ApiResponse(responseCode = "201", description = "Pago registrado exitosamente")
    @PostMapping
    public ResponseEntity<PaymentResponseDto> save(@Valid @RequestBody PaymentRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.save(request));
    }

    @Operation(summary = "Actualizar pago")
    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody PaymentRequestDto request) {
        return ResponseEntity.ok(paymentService.update(id, request));
    }

    @Operation(summary = "Eliminar pago")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        paymentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Ver mis pagos", description = "El cliente logueado ve los pagos de sus propios pedidos")
    @GetMapping("/my")
    public ResponseEntity<List<PaymentResponseDto>> getMyPayments(@AuthenticationPrincipal UserEntity currentUser) {
        return ResponseEntity.ok(paymentService.getMyPayments(currentUser.getId()));
    }
}