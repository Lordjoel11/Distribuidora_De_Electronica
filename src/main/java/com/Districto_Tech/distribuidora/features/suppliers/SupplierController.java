package com.Districto_Tech.distribuidora.features.suppliers;

import com.Districto_Tech.distribuidora.features.suppliers.dto.SupplierRequestDto;
import com.Districto_Tech.distribuidora.features.suppliers.dto.SupplierResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Suppliers", description = "Gestión de proveedores")
@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @Operation(summary = "Listar todos los proveedores")
    @GetMapping
    public ResponseEntity<List<SupplierResponseDto>> getAll() {
        return ResponseEntity.ok(supplierService.getAll());
    }

    @Operation(summary = "Obtener proveedor por Id")
    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getById(id));
    }

    @Operation(summary = "Crear proveedor", description = "Registra un nuevo proveedor")
    @ApiResponse(responseCode = "201", description = "Proveedor creado exitosamente")
    @PostMapping
    public ResponseEntity<SupplierResponseDto> save(@Valid @RequestBody SupplierRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierService.save(request));
    }

    @Operation(summary = "Actualizar proveedor por Id")
    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody SupplierRequestDto request) {
        return ResponseEntity.ok(supplierService.update(id, request));
    }

    @Operation(summary = "Eliminar proveedor por Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        supplierService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}