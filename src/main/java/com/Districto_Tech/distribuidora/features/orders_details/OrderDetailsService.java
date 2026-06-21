package com.Districto_Tech.distribuidora.features.orders_details;

import com.Districto_Tech.distribuidora.common.exceptions.ResourceNotFoundException;
import com.Districto_Tech.distribuidora.features.orders_details.dto.OrderDetailsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderDetailsModelMapper orderDetailsMapper;

    public List<OrderDetailsResponseDto> getAll() {
        return orderDetailsRepository.findAll().stream()
                .map(orderDetailsMapper::toDto).toList();
    }

    public OrderDetailsResponseDto getById(Long id) {
        return orderDetailsMapper.toDto(orderDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle de pedido no encontrado.")));
    }

    public List<OrderDetailsResponseDto> getByOrderId(Long orderId) {
        return orderDetailsRepository.findAllByOrder_Id(orderId).stream()
                .map(orderDetailsMapper::toDto).toList();
    }
}