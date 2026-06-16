package com.Districto_Tech.distribuidora.features.reports;

import com.Districto_Tech.distribuidora.features.products.Product;
import com.Districto_Tech.distribuidora.features.products.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor

public class ReportController {

    private final ReportService reportService;
    private final ProductService productService;

    @GetMapping("/sales")

    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SalesReportsDto> getVentasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
    ) {
        return ResponseEntity.ok(reportService.getVentasPorPeriodo(start, end));
    }



    @GetMapping("/stock-bajo")
    @PreAuthorize("hasRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<List<Product>> getStockBajo(
            @RequestParam(defaultValue = "5") Integer umbral) {

        List<Product> productos = productService.getLowStockProducts(umbral);
        return ResponseEntity.ok(productos);
    }

}