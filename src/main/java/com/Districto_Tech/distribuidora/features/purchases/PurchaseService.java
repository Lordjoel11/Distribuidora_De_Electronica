package com.Districto_Tech.distribuidora.features.purchases;

import com.Districto_Tech.distribuidora.common.exceptions.ResourceNotFoundException;
import com.Districto_Tech.distribuidora.features.products.Product;
import com.Districto_Tech.distribuidora.features.products.ProductRepository;
import com.Districto_Tech.distribuidora.features.purchases_details.PurchaseDetails;
import com.Districto_Tech.distribuidora.features.purchases_details.dto.PurchaseDetailsRequestDto;
import com.Districto_Tech.distribuidora.features.purchases.dto.PurchaseRequestDto;
import com.Districto_Tech.distribuidora.features.purchases.dto.PurchaseResponseDto;
import com.Districto_Tech.distribuidora.features.purchases_details.PurchaseDetailsRepository;
import com.Districto_Tech.distribuidora.features.suppliers.Supplier;
import com.Districto_Tech.distribuidora.features.suppliers.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseDetailsRepository purchaseDetailsRepository;
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final PurchaseMapper purchaseMapper;

    @Transactional
    public PurchaseResponseDto save(PurchaseRequestDto dto) {
        Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado."));

        PurchaseEntity purchase = PurchaseEntity.builder()
                .purchaseDate(LocalDate.now())
                .purchaseStatus(PurchaseStatus.PENDING)
                .supplier(supplier)
                .build();

        PurchaseEntity savedPurchase = purchaseRepository.save(purchase);

        List<PurchaseDetails> detailsList = new ArrayList<>();

        for (PurchaseDetailsRequestDto detailDto : dto.getPurchaseDetails()) {
            Product product = productRepository.findById(detailDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado."));

            PurchaseDetails detail = PurchaseDetails.builder()
                    .purchase(savedPurchase)
                    .product(product)
                    .quantity(detailDto.getQuantity())
                    .build();

            detailsList.add(purchaseDetailsRepository.save(detail));
        }
        savedPurchase.setPurchaseDetails(detailsList);

        return purchaseMapper.toDto(savedPurchase);
    }

    public List<PurchaseResponseDto> getAll() {
        return purchaseRepository.findAll().stream()
                .map(purchaseMapper::toDto).toList();
    }

    public PurchaseResponseDto getById(Long id) {
        return purchaseMapper.toDto(purchaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compra no encontrada.")));
    }

    public void delete(Long id) {
        purchaseRepository.deleteById(id);
    }

    @Transactional
    public PurchaseResponseDto completePurchase(Long id) {
        PurchaseEntity purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compra no encontrada."));

        if (purchase.getPurchaseStatus() == PurchaseStatus.CANCELED) {
            throw new IllegalStateException("No se puede completar una compra cancelada.");
        }

        if (purchase.getPurchaseStatus() == PurchaseStatus.COMPLETED) {
            throw new IllegalStateException("La compra esta actualmete compeltada.");
        }

        for (PurchaseDetails detail : purchase.getPurchaseDetails()) {
            Product product = detail.getProduct();
            product.setStock(product.getStock() + detail.getQuantity());
            productRepository.save(product);
        }

        purchase.setPurchaseStatus(PurchaseStatus.COMPLETED);
        return purchaseMapper.toDto(purchaseRepository.save(purchase));
    }

    @Transactional
    public PurchaseResponseDto cancelPurchase(Long id) {
        PurchaseEntity purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compra no encontrada."));

        if (purchase.getPurchaseStatus() == PurchaseStatus.COMPLETED) {
            throw new IllegalStateException("No se puede cancelar una compra COMPLETADA.");
        }
        if (purchase.getPurchaseStatus() == PurchaseStatus.CANCELED) {
            throw new IllegalStateException("La compra ya está cancelada.");
        }

        purchase.setPurchaseStatus(PurchaseStatus.CANCELED);
        return purchaseMapper.toDto(purchaseRepository.save(purchase));
    }


}