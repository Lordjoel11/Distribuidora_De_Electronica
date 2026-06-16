package com.Districto_Tech.distribuidora.features.shipping;

import com.Districto_Tech.distribuidora.common.IService;
import com.Districto_Tech.distribuidora.common.exceptions.ShippingAlreadyExistsException;
import com.Districto_Tech.distribuidora.common.exceptions.ShippingNotFoundException;
import com.Districto_Tech.distribuidora.features.shipping.dtos.ShippingRequestDTO;
import com.Districto_Tech.distribuidora.features.shipping.dtos.ShippingResponseDTO;
import com.Districto_Tech.distribuidora.features.users.dto.UserResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ShippingService implements IService<ShippingRequestDTO, ShippingResponseDTO, UUID> {

    private ShippingRepository shippingRepository;
    private ShippingModelMapper shippingModelMapper;

    @Override
    public ShippingResponseDTO save(ShippingRequestDTO request) {

        if(shippingRepository.existsById(shippingModelMapper.toEntity(request).getUuid())){
            throw new ShippingAlreadyExistsException("Shipment already exists");
        }

        ShippingEntity shippingEntity = shippingModelMapper.toEntity(request);
        shippingRepository.save(shippingEntity);

        return shippingModelMapper.toDto(shippingEntity);
    }

    @Override
    public List<ShippingResponseDTO> getAll() {
        return shippingRepository.findAll().stream().map(shippingModelMapper::toDto).toList();
    }


    @Override
    public ShippingResponseDTO getById(UUID ID) {
        ShippingEntity entity = shippingRepository.findById(ID).orElseThrow(() -> new ShippingNotFoundException("Shipment not found"));
        shippingRepository.save(entity);
        return shippingModelMapper.toDto(entity);
    }


    @Override
    public ShippingResponseDTO update(UUID ID, ShippingRequestDTO request) {
        shippingRepository.findById(ID).orElseThrow(() -> new ShippingNotFoundException("Shipment not found"));
        ShippingEntity entity=shippingModelMapper.toEntity(request);
        shippingRepository.save(entity);
        return shippingModelMapper.toDto(entity);
    }

    @Override
    public void deleteById(UUID ID) {
        shippingRepository.delete(shippingRepository.findById(ID).orElseThrow(() -> new ShippingNotFoundException("Shipment not found")));
    }
}
