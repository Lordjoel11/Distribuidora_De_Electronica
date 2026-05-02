package com.Districto_Tech.distribuidora.features.products.service;

import com.Districto_Tech.distribuidora.features.products.dto.ProductDTO;
import com.Districto_Tech.distribuidora.features.products.entity.ProductsEntity;
import com.Districto_Tech.distribuidora.features.products.dto.ProductMapper;
import com.Districto_Tech.distribuidora.features.products.repository.ProductRepository;
import com.Districto_Tech.distribuidora.features.products.service.ProductService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductDTO crear(ProductDTO dto) {
        ProductsEntity entity = ProductMapper.toEntity(dto);
        ProductsEntity guardado = repository.save(entity);
        return ProductMapper.toDTO(guardado);
    }

    @Override
    public List<ProductDTO> listar() {
        return repository.findAll()
                .stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO obtenerPorPublicId(String publicId) {
        ProductsEntity producto = repository.findByPublicId(publicId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        return ProductMapper.toDTO(producto);
    }

    @Override
    public ProductDTO actualizar(String publicId, ProductDTO dto) {
        ProductsEntity producto = repository.findByPublicId(publicId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // update limpio (sin tocar publicId)
        ProductMapper.updateEntity(producto, dto);

        ProductsEntity actualizado = repository.save(producto);

        return ProductMapper.toDTO(actualizado);
    }

    @Override
    public void eliminar(String publicId) {
        ProductsEntity producto = repository.findByPublicId(publicId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        repository.delete(producto);
    }
}