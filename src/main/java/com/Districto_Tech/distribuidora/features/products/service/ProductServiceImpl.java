package com.Districto_Tech.distribuidora.features.products.service;

import com.Districto_Tech.distribuidora.features.products.dto.ProductDTO;
import com.Districto_Tech.distribuidora.features.products.entity.ProductEntity;
import com.Districto_Tech.distribuidora.features.products.dto.ProductMapper;
import com.Districto_Tech.distribuidora.features.products.repository.ProductRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    //Inyeccion de dependencias
    public ProductServiceImpl(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ProductDTO crear(ProductDTO dto) {
        ProductEntity entity = mapper.toEntityRQ(dto);
        ProductEntity guardado = repository.save(entity);
        return mapper.toResponseDto(guardado);
    }

    @Override
    public List<ProductDTO> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO obtenerPorPublicId(String publicId) {
        ProductEntity producto = repository.findByPublicId(publicId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        return mapper.toResponseDto(producto);
    }

    @Override
    public ProductDTO actualizar(String publicId, ProductDTO dto) {
        ProductEntity producto = repository.findByPublicId(publicId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        mapper.updateEntity(producto, dto);

        ProductEntity actualizado = repository.save(producto);

        return mapper.toResponseDto(actualizado);
    }

    @Override
    public void eliminar(String publicId) {
        ProductEntity producto = repository.findByPublicId(publicId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        repository.delete(producto);
    }
}