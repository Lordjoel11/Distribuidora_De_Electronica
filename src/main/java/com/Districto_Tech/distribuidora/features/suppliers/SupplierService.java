package com.Districto_Tech.distribuidora.features.suppliers;

import com.Districto_Tech.distribuidora.common.IService;
import com.Districto_Tech.distribuidora.common.exceptions.ResourceNotFoundException;
import com.Districto_Tech.distribuidora.features.suppliers.dto.SupplierRequestDto;
import com.Districto_Tech.distribuidora.features.suppliers.dto.SupplierResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierService implements IService<SupplierRequestDto, SupplierResponseDto, Long> {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    @Override
    public SupplierResponseDto save(SupplierRequestDto request) {
        Supplier supplier = supplierMapper.toEntity(request);
        return supplierMapper.toDto(supplierRepository.save(supplier));
    }

    @Override
    public List<SupplierResponseDto> getAll() {
        return supplierRepository.findAll()
                .stream()
                .map(supplierMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierResponseDto getById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado."));
        return supplierMapper.toDto(supplier);
    }

    @Override
    public SupplierResponseDto update(Long id, SupplierRequestDto request) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado."));

        supplier.setName(request.getName());
        supplier.setCuit(request.getCuit());
        supplier.setContact(request.getContact());

        return supplierMapper.toDto(supplierRepository.save(supplier));
    }

    @Override
    public void deleteById(Long id) {
        supplierRepository.deleteById(id);
    }
}