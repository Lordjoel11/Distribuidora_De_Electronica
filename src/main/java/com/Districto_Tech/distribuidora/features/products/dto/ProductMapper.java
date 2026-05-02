package com.Districto_Tech.distribuidora.features.products.dto;

import com.Districto_Tech.distribuidora.features.products.entity.ProductEntity;

public class ProductMapper {

    public static ProductDTO toDTO(ProductEntity producto) {
        if (producto == null) return null;

        return ProductDTO.builder()
                .publicId(producto.getPublicId())
                .nombre(producto.getNombre())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .build();
    }

    public static ProductEntity toEntity(ProductDTO dto) {
        if (dto == null) return null;

        return ProductEntity.builder()
                .nombre(dto.getNombre())
                .precio(dto.getPrecio())
                .stock(dto.getStock())
                .build();
    }

    public static void updateEntity(ProductEntity entity, ProductDTO dto) {
        entity.setNombre(dto.getNombre());
        entity.setPrecio(dto.getPrecio());
        entity.setStock(dto.getStock());
    }
}