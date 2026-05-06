package com.Districto_Tech.distribuidora.features.products.dto;

import com.Districto_Tech.distribuidora.common.IMapperConvertGlobalObjects;
import com.Districto_Tech.distribuidora.features.products.dto.ProductDTO;
import com.Districto_Tech.distribuidora.features.products.entity.ProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements IMapperConvertGlobalObjects<ProductEntity, ProductDTO, ProductDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductEntity toEntityRQ(ProductDTO dto) {
        ProductEntity entity = modelMapper.map(dto, ProductEntity.class);

        entity.setPublicId(null);

        return entity;
    }

    @Override
    public ProductEntity toEntityRS(ProductDTO dto) {
        return modelMapper.map(dto, ProductEntity.class);
    }

    @Override
    public ProductDTO toResponseDto(ProductEntity entity) {
        return modelMapper.map(entity, ProductDTO.class);
    }

    @Override
    public ProductDTO toRequestDto(ProductEntity entity) {
        return modelMapper.map(entity, ProductDTO.class);
    }

    public void updateEntity(ProductEntity entity, ProductDTO dto) {
        entity.setNombre(dto.getNombre());
        entity.setPrecio(dto.getPrecio());
        entity.setStock(dto.getStock());
    }
}