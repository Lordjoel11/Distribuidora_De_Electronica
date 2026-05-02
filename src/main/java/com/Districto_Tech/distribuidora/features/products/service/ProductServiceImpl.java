package com.Districto_Tech.distribuidora.features.products.service;

import com.Districto_Tech.distribuidora.features.products.dto.ProductDTO;
import com.Districto_Tech.distribuidora.features.products.entity.ProductEntity;
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
        ProductEntity entity = ProductMapper.toEntity(dto);
        ProductEntity guardado = repository.save(entity);
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
        ProductEntity producto = repository.findByPublicId(publicId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        return ProductMapper.toDTO(producto);
    }

    @Override
    public ProductDTO actualizar(String publicId, ProductDTO dto) {
        ProductEntity producto = repository.findByPublicId(publicId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));


        ProductMapper.updateEntity(producto, dto);

        ProductEntity actualizado = repository.save(producto);

        return ProductMapper.toDTO(actualizado);
    }

    @Override
    public void eliminar(String publicId) {
        ProductEntity producto = repository.findByPublicId(publicId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        repository.delete(producto);
    }
}