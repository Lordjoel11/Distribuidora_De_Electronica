package com.Districto_Tech.distribuidora.features.suppliers;

import com.Districto_Tech.distribuidora.common.IService;
import com.Districto_Tech.distribuidora.common.exceptions.ResourceNotFoundException;
import com.Districto_Tech.distribuidora.features.suppliers.dto.SupplierRequestDto;
import com.Districto_Tech.distribuidora.features.suppliers.dto.SupplierResponseDto;
import com.Districto_Tech.distribuidora.features.users.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierService implements IService<SupplierRequestDto, SupplierResponseDto, Long> {

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    @Override
    public SupplierResponseDto save(SupplierRequestDto request) {
        Supplier supplier = modelMapper.map(request, Supplier.class);
        return modelMapper.map(supplierRepository.save(supplier), SupplierResponseDto.class);
    }

    @Override
    public List<SupplierResponseDto> getAll() {
        return supplierRepository.findAll()
                .stream()
                .map(supplier -> modelMapper.map(supplier, SupplierResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public SupplierResponseDto getById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado."));
        return modelMapper.map(supplier, SupplierResponseDto.class);
    }

    @Override
    public SupplierResponseDto update(Long id, SupplierRequestDto request) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado."));

        supplier.setName(request.getName());
        supplier.setCuit(request.getCuit());
        supplier.setContact(request.getContact());

        return modelMapper.map(supplierRepository.save(supplier), SupplierResponseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        supplierRepository.deleteById(id);
    }
}