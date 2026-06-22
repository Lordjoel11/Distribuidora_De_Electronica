package com.Districto_Tech.distribuidora.features.purchases;

import com.Districto_Tech.distribuidora.features.purchases.dto.PurchaseRequestDto;
import com.Districto_Tech.distribuidora.features.purchases.dto.PurchaseResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Purchases", description = "Gestión de compras a proveedores")
@RestController
@RequestMapping("/api/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Operation(summary = "Crear nueva compra", description = "Crea una compra con sus detalles asociados")
    @ApiResponse(responseCode = "201", description = "Compra creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos") @GetMapping
    public ResponseEntity<List<PurchaseResponseDto>> getAll() {
        return ResponseEntity.ok(purchaseService.getAll());
    }

    @Operation(summary = "Listar todas las compras")
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseService.getById(id));
    }

    @Operation(summary = "Obtener compra por ID")
    @PostMapping
    public ResponseEntity<PurchaseResponseDto> save(@Valid @RequestBody PurchaseRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseService.save(dto));
    }

    @Operation(summary = "Eliminar por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        purchaseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Completar compra", description = "Marca la compra como COMPLETED e incrementa stock")
    @PatchMapping("/{id}/complete")
    public ResponseEntity<PurchaseResponseDto> completePurchase(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseService.completePurchase(id));
    }

    @Operation(summary = "Cancelar compra")
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<PurchaseResponseDto> cancelPurchase(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseService.cancelPurchase(id));
    }

}