package com.Districto_Tech.distribuidora.features.products.dto;

import com.Districto_Tech.distribuidora.features.products.entity.productsEntity;

public class ProductMapper {

    public static ProductDTO toDTO(productsEntity producto) {
        if (producto == null) return null;

        return ProductDTO.builder()
                .publicId(producto.getPublicId())
                .nombre(producto.getNombre())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .build();
    }

    public static productsEntity toEntity(ProductDTO dto) {
        if (dto == null) return null;

        return productsEntity.builder()
                .nombre(dto.getNombre())
                .precio(dto.getPrecio())
                .stock(dto.getStock())
                .build();
    }
}