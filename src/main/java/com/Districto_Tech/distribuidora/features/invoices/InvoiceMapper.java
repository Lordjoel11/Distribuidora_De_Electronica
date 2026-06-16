package com.Districto_Tech.distribuidora.features.invoices;

import com.Districto_Tech.distribuidora.common.IModelMapper;
import com.Districto_Tech.distribuidora.common.config.MapperConfig;
import com.Districto_Tech.distribuidora.features.invoices.dto.InvoiceRequestDto;
import com.Districto_Tech.distribuidora.features.invoices.dto.InvoiceResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InvoiceMapper implements IModelMapper<InvoiceEntity, InvoiceResponseDto, InvoiceRequestDto> {

    private final MapperConfig mapperConfig;

    @Override
    public InvoiceEntity toEntity(InvoiceRequestDto dto) {
        return mapperConfig.modelMapper().map(dto, InvoiceEntity.class);
    }

    @Override
    public InvoiceResponseDto toDto(InvoiceEntity entity) {
        InvoiceResponseDto dto = mapperConfig.modelMapper().map(entity, InvoiceResponseDto.class);
        if (entity.getOrder() != null) {
            dto.setOrderId(entity.getOrder().getId());
        }
        return dto;
    }
}