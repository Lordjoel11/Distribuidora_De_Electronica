package com.Districto_Tech.distribuidora.features.products;

import com.Districto_Tech.distribuidora.common.exceptions.ProductoNoEncontradoException;
import com.Districto_Tech.distribuidora.features.products.dto.ProductRequest;
import com.Districto_Tech.distribuidora.features.products.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException("Producto no encontrado."));

        return modelMapper.map(product, ProductResponse.class);
    }

    public ProductResponse save(ProductRequest request) {
        Product product = modelMapper.map(request, Product.class);
        Product saved = productRepository.save(product);
        return modelMapper.map(saved, ProductResponse.class);
    }

    public ProductResponse update(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException("Producto no encontrado."));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setStock(request.getStock());
        product.setUnitPrice(request.getUnitPrice());
        product.setCategory(request.getCategory());

        return modelMapper.map(productRepository.save(product), ProductResponse.class);
    }

}

