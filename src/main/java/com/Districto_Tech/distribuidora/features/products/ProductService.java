package com.Districto_Tech.distribuidora.features.products;

import com.Districto_Tech.distribuidora.common.IService;
import com.Districto_Tech.distribuidora.common.exceptions.ResourceNotFoundException;
import com.Districto_Tech.distribuidora.features.products.dto.ProductRequestDto;
import com.Districto_Tech.distribuidora.features.products.dto.ProductResponseDto;
import com.Districto_Tech.distribuidora.features.users.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements IService<ProductRequestDto, ProductResponseDto, Long> {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;


    @Override
    public ProductResponseDto save(ProductRequestDto request) {
        Product product = modelMapper.map(request, Product.class);
        Product saved = productRepository.save(product);
        return modelMapper.map(saved, ProductResponseDto.class);
    }

    @Override
    public List<ProductResponseDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado."));

        return modelMapper.map(product, ProductResponseDto.class);
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

        return modelMapper.map(productRepository.save(product), ProductResponseDto.class);
    }

    @Override
    public void deleteById(Long id){productRepository.deleteById(id);}


    

}

