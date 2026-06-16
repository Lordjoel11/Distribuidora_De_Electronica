package com.Districto_Tech.distribuidora.features.suppliers;

import com.Districto_Tech.distribuidora.common.exceptions.ResourceNotFoundException;
import com.Districto_Tech.distribuidora.features.suppliers.dto.SupplierRequestDto;
import com.Districto_Tech.distribuidora.features.suppliers.dto.SupplierResponseDto;
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
class SupplierServiceTest {

    @Mock
    private SupplierRepository supplierRepository;

    @Mock
    private SupplierMapper supplierMapper;

    @InjectMocks
    private SupplierService supplierService;

    private Supplier supplier;
    private SupplierRequestDto requestDto;
    private SupplierResponseDto responseDto;

    @BeforeEach
    void setUp() {
        supplier = Supplier.builder()
                .id(1L)
                .name("Tech Distributors SA")
                .cuit("30-12345678-9")
                .contact("contacto@techdist.com")
                .build();

        requestDto = SupplierRequestDto.builder()
                .name("Tech Distributors SA")
                .cuit("30-12345678-9")
                .contact("contacto@techdist.com")
                .build();

        responseDto = SupplierResponseDto.builder()
                .id(1L)
                .name("Tech Distributors SA")
                .cuit("30-12345678-9")
                .contact("contacto@techdist.com")
                .build();
    }

    @Test
    void save_ShouldCreateSupplierSuccessfully() {
        when(supplierMapper.toEntity(any(SupplierRequestDto.class))).thenReturn(supplier);
        when(supplierRepository.save(any(Supplier.class))).thenReturn(supplier);
        when(supplierMapper.toDto(any(Supplier.class))).thenReturn(responseDto);

        SupplierResponseDto result = supplierService.save(requestDto);

        assertNotNull(result);
        assertEquals("Tech Distributors SA", result.getName());
        verify(supplierRepository).save(any());
    }

    @Test
    void getAll_ShouldReturnAllSuppliers() {
        when(supplierRepository.findAll()).thenReturn(List.of(supplier));
        when(supplierMapper.toDto(any(Supplier.class))).thenReturn(responseDto);

        List<SupplierResponseDto> result = supplierService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void getById_WhenSupplierExists_ShouldReturnSupplier() {
        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));
        when(supplierMapper.toDto(any(Supplier.class))).thenReturn(responseDto);

        SupplierResponseDto result = supplierService.getById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void getById_WhenSupplierNotExists_ShouldThrowException() {
        when(supplierRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> supplierService.getById(99L));
    }

    @Test
    void update_ShouldUpdateSupplierSuccessfully() {
        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));
        when(supplierRepository.save(any(Supplier.class))).thenReturn(supplier);
        when(supplierMapper.toDto(any(Supplier.class))).thenReturn(responseDto);

        SupplierResponseDto result = supplierService.update(1L, requestDto);

        assertNotNull(result);
        verify(supplierRepository).save(any());
    }

    @Test
    void update_WhenSupplierNotExists_ShouldThrowException() {
        when(supplierRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> supplierService.update(99L, requestDto));
    }

    @Test
    void deleteById_WhenSupplierExists_ShouldDeleteSuccessfully() {
        when(supplierRepository.existsById(1L)).thenReturn(true); // Aunque no está en el service actual, lo incluimos por consistencia
        doNothing().when(supplierRepository).deleteById(1L);

        assertDoesNotThrow(() -> supplierService.deleteById(1L));

        verify(supplierRepository).deleteById(1L);
    }

    @Test
    void deleteById_WhenSupplierNotExists_ShouldThrowException() {
        when(supplierRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> supplierService.deleteById(99L));
    }
}