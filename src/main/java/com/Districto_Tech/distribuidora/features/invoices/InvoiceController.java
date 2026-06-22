package com.Districto_Tech.distribuidora.features.invoices;

import com.Districto_Tech.distribuidora.features.invoices.dto.InvoiceRequestDto;
import com.Districto_Tech.distribuidora.features.invoices.dto.InvoiceResponseDto;
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
@Tag(name = "Invoices", description = "Gestión de facturas")
@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Operation(summary = "Listar todas las facturas")
    @GetMapping
    public ResponseEntity<List<InvoiceResponseDto>> getAll() {
        return ResponseEntity.ok(invoiceService.getAll());
    }

    @Operation(summary = "Obtener factura por ID")
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(invoiceService.getById(id));
    }

    @Operation(summary = "Obtener factura por Pedido")
    @GetMapping("/order/{orderId}")
    public ResponseEntity<InvoiceResponseDto> getByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(invoiceService.getByOrderId(orderId));
    }

    @Operation(summary = "Crear factura", description = "Genera una factura para un pedido")
    @ApiResponse(responseCode = "201", description = "Factura creada exitosamente")
    @PostMapping
    public ResponseEntity<InvoiceResponseDto> save(@Valid @RequestBody InvoiceRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceService.save(dto));
    }

    @Operation(summary = "Eliminar Pedido")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        invoiceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Ver mis facturas", description = "El cliente logueado ve las facturas de sus propios pedidos")
    @GetMapping("/my")
    public ResponseEntity<List<InvoiceResponseDto>> getMyInvoices(@AuthenticationPrincipal UserEntity currentUser) {
        return ResponseEntity.ok(invoiceService.getMyInvoices(currentUser.getId()));
    }

}