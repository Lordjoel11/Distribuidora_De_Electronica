package com.Districto_Tech.distribuidora.features.purchases;

import com.Districto_Tech.distribuidora.common.IModelMapper;
import com.Districto_Tech.distribuidora.common.config.MapperConfig;
import com.Districto_Tech.distribuidora.features.purchases.dto.PurchaseDetailsResponseDto;
import com.Districto_Tech.distribuidora.features.purchases.dto.PurchaseRequestDto;
import com.Districto_Tech.distribuidora.features.purchases.dto.PurchaseResponseDto;
import com.Districto_Tech.distribuidora.features.purchases_details.dto.PurchaseDetailsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PurchaseMapper implements IModelMapper<PurchaseEntity, PurchaseResponseDto, PurchaseRequestDto> {

    private final MapperConfig mapperConfig;

    @Override
    public PurchaseEntity toEntity(PurchaseRequestDto dto) {
        return mapperConfig.modelMapper().map(dto, PurchaseEntity.class);
    }

    @Override
    public PurchaseResponseDto toDto(PurchaseEntity entity) {
        PurchaseResponseDto dto = mapperConfig.modelMapper().map(entity, PurchaseResponseDto.class);
        if (entity.getSupplier() != null) {
            dto.setSupplierName(entity.getSupplier().getName());
        }
        if (entity.getPurchaseDetails() != null) {
            List<com.Districto_Tech.distribuidora.features.purchases_details.dto.PurchaseDetailsResponseDto> details = entity.getPurchaseDetails().stream()
                    .map(detail -> {
                        com.Districto_Tech.distribuidora.features.purchases_details.dto.PurchaseDetailsResponseDto d = new PurchaseDetailsResponseDto();
                        d.setId(detail.getId());
                        d.setQuantity(detail.getQuantity());
                        if (detail.getProduct() != null) {
                            d.setProductId(detail.getProduct().getId());
                            d.setProductName(detail.getProduct().getName());
                        }
                        return d;
                    }).toList();
            dto.setPurchaseDetails(details);
        }
        return dto;
    }
}