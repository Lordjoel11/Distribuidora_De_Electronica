package com.Districto_Tech.distribuidora.features.products;

import com.Districto_Tech.distribuidora.common.exceptions.ResourceNotFoundException;
import com.Districto_Tech.distribuidora.features.products.dto.ProductRequestDto;
import com.Districto_Tech.distribuidora.features.products.dto.ProductResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private ProductRequestDto requestDto;
    private ProductResponseDto responseDto;

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .id(1L)
                .name("Laptop Gaming")
                .description("Laptop potente")
                .stock(15)
                .unitPrice(1250.0)
                .category(Category.ELECTRONICS)
                .build();

        requestDto = ProductRequestDto.builder()
                .name("Laptop Gaming")
                .description("Laptop potente")
                .stock(15)
                .unitPrice(1250.0)
                .category(Category.ELECTRONICS)
                .build();

        responseDto = ProductResponseDto.builder()
                .id(1L)
                .name("Laptop Gaming")
                .description("Laptop potente")
                .stock(15)
                .unitPrice(1250.0)
                .category(Category.ELECTRONICS)
                .build();
    }

    @Test
    void save_ShouldSaveProductSuccessfully() {
        when(productMapper.toEntity(any(ProductRequestDto.class))).thenReturn(product);
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productMapper.toDto(any(Product.class))).thenReturn(responseDto);

        ProductResponseDto result = productService.save(requestDto);

        assertNotNull(result);
        assertEquals("Laptop Gaming", result.getName());
        verify(productRepository, times(1)).save(any(Product.class));
        verify(productMapper, times(1)).toEntity(any());
        verify(productMapper, times(1)).toDto(any());
    }

    @Test
    void getAll_ShouldReturnAllProducts() {
        when(productRepository.findAll()).thenReturn(List.of(product));
        when(productMapper.toDto(any(Product.class))).thenReturn(responseDto);

        List<ProductResponseDto> result = productService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Laptop Gaming", result.get(0).getName());
    }

    @Test
    void getById_WhenProductExists_ShouldReturnProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.toDto(any(Product.class))).thenReturn(responseDto);

        ProductResponseDto result = productService.getById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Laptop Gaming", result.getName());
    }

    @Test
    void getById_WhenProductNotExists_ShouldThrowResourceNotFoundException() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.getById(99L));
    }

    @Test
    void update_ShouldUpdateProductSuccessfully() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productMapper.toDto(any(Product.class))).thenReturn(responseDto);

        ProductResponseDto result = productService.update(1L, requestDto);

        assertNotNull(result);
        verify(productRepository).findById(1L);
        verify(productRepository).save(any());
    }

    @Test
    void update_WhenProductNotExists_ShouldThrowException() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.update(99L, requestDto));
    }

    @Test
    void deleteById_WhenProductExists_ShouldDeleteSuccessfully() {
        when(productRepository.existsById(1L)).thenReturn(true);
        doNothing().when(productRepository).deleteById(1L);

        assertDoesNotThrow(() -> productService.deleteById(1L));

        verify(productRepository).existsById(1L);
        verify(productRepository).deleteById(1L);
    }

    @Test
    void deleteById_WhenProductNotExists_ShouldThrowException() {
        when(productRepository.existsById(99L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> productService.deleteById(99L));
    }

}