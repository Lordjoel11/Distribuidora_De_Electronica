package com.Districto_Tech.distribuidora.features.products;

import com.Districto_Tech.distribuidora.common.IModelMapper;
import com.Districto_Tech.distribuidora.common.config.MapperConfig;
import com.Districto_Tech.distribuidora.features.products.dto.ProductRequestDto;
import com.Districto_Tech.distribuidora.features.products.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper implements IModelMapper<Product, ProductResponseDto, ProductRequestDto> {

    private final MapperConfig mapperConfig;

    @Override
    public Product toEntity(ProductRequestDto dto) {
        return mapperConfig.modelMapper().map(dto, Product.class);
    }

    @Override
    public ProductResponseDto toDto(Product product) {
        return mapperConfig.modelMapper().map(product, ProductResponseDto.class);
    }
}