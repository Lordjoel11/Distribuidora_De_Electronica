package com.Districto_Tech.distribuidora.features.shipping;

import com.Districto_Tech.distribuidora.common.IService;
import com.Districto_Tech.distribuidora.features.shipping.dtos.ShippingRequestDTO;
import com.Districto_Tech.distribuidora.features.shipping.dtos.ShippingResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingService implements IService<ShippingRequestDTO, ShippingResponseDTO, Integer> {

    private ShippingRepository shippingRepository;
    private ShippingModelMapper shippingModelMapper;

    @Override
    public ShippingResponseDTO save(ShippingRequestDTO request) {
        if(shippingRepository.existsById(request.getUuid()));{
            throw new
        }
    }

    @Override
    public List<ShippingResponseDTO> getAll() {
        return List.of();
    }

    @Override
    public ShippingResponseDTO getById(Integer integer) {
        return null;
    }

    @Override
    public ShippingResponseDTO update(Integer integer, ShippingRequestDTO request) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }
}
