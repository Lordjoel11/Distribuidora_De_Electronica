package com.Districto_Tech.distribuidora.features.products.service;

import com.Districto_Tech.distribuidora.features.products.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO crear(ProductDTO dto);

    List<ProductDTO> listar();

    ProductDTO obtenerPorPublicId(String publicId);

    ProductDTO actualizar(String publicId, ProductDTO dto);

    void eliminar(String publicId);
}