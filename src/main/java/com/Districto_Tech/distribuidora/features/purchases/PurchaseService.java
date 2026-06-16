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
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found."));

        PurchaseEntity purchase = PurchaseEntity.builder()
                .purchaseDate(LocalDate.now())
                .purchaseStatus(PurchaseStatus.PENDING)
                .supplier(supplier)
                .build();

        PurchaseEntity saved = purchaseRepository.save(purchase);

        for (PurchaseDetailsRequestDto detailDto : dto.getPurchaseDetails()) {
            Product product = productRepository.findById(detailDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found."));

            PurchaseDetails detail = PurchaseDetails.builder()
                    .purchase(saved)
                    .product(product)
                    .quantity(detailDto.getQuantity())
                    .build();

            purchaseDetailsRepository.save(detail);
        }

        return purchaseMapper.toDto(purchaseRepository.findById(saved.getId()).orElseThrow());
    }

    public List<PurchaseResponseDto> getAll() {
        return purchaseRepository.findAll().stream()
                .map(purchaseMapper::toDto).toList();
    }

    public PurchaseResponseDto getById(Long id) {
        return purchaseMapper.toDto(purchaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase not found.")));
    }

    public void delete(Long id) {
        purchaseRepository.deleteById(id);
    }
}