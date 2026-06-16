package com.Districto_Tech.distribuidora.features.suppliers;
import com.Districto_Tech.distribuidora.common.IModelMapper;
import com.Districto_Tech.distribuidora.common.config.MapperConfig;
import com.Districto_Tech.distribuidora.features.suppliers.dto.SupplierRequestDto;
import com.Districto_Tech.distribuidora.features.suppliers.dto.SupplierResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupplierMapper implements IModelMapper<Supplier, SupplierResponseDto, SupplierRequestDto> {

    private final MapperConfig mapperConfig;

    @Override
    public Supplier toEntity(SupplierRequestDto dto) {
        return mapperConfig.modelMapper().map(dto, Supplier.class);
    }

    @Override
    public SupplierResponseDto toDto(Supplier supplier) {
        return mapperConfig.modelMapper().map(supplier, SupplierResponseDto.class);
    }
}