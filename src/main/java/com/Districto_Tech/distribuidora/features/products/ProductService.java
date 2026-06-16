package com.Districto_Tech.distribuidora.features.products;

import com.Districto_Tech.distribuidora.common.IService;
import com.Districto_Tech.distribuidora.common.exceptions.ResourceNotFoundException;
import com.Districto_Tech.distribuidora.features.products.dto.ProductRequestDto;
import com.Districto_Tech.distribuidora.features.products.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements IService<ProductRequestDto, ProductResponseDto, Long> {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDto save(ProductRequestDto request) {
        Product product = productMapper.toEntity(request);
        return productMapper.toDto(productRepository.save(product));
    }


    public List<Product> getLowStockProducts(Integer threshold) {
        return productRepository.findByStockLessThanEqual(threshold);
    }

    @Override
    public List<ProductResponseDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado."));
        return productMapper.toDto(product);
    }

    @Override
    public ProductResponseDto update(Long id, ProductRequestDto request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado."));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setStock(request.getStock());
        product.setUnitPrice(request.getUnitPrice());
        product.setCategory(request.getCategory());

        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public List<ProductResponseDto> getLowStock(Integer threshold) {
        return productRepository.findByStockLessThanEqual(threshold)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }
}

