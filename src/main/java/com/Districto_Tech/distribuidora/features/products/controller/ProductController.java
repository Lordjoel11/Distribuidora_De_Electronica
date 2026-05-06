package com.Districto_Tech.distribuidora.features.products.controller;

import com.Districto_Tech.distribuidora.features.products.dto.ProductDTO;
import com.Districto_Tech.distribuidora.features.products.service.ProductService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> crear(@Valid @RequestBody ProductDTO dto) {
        return ResponseEntity.status(201).body(service.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{publicId}")
    public ResponseEntity<ProductDTO> obtener(@PathVariable String publicId) {
        return ResponseEntity.ok(service.obtenerPorPublicId(publicId));
    }

    @PutMapping("/{publicId}")
    public ResponseEntity<ProductDTO> actualizar(
            @PathVariable String publicId,
            @Valid @RequestBody ProductDTO dto) {

        return ResponseEntity.ok(service.actualizar(publicId, dto));
    }

    @DeleteMapping("/{publicId}")
    public ResponseEntity<Void> eliminar(@PathVariable String publicId) {
        service.eliminar(publicId);
        return ResponseEntity.noContent().build();
    }
}