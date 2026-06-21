package com.Districto_Tech.distribuidora.features.orders;

import com.Districto_Tech.distribuidora.common.IModelMapper;
import com.Districto_Tech.distribuidora.common.exceptions.ResourceNotFoundException;
import com.Districto_Tech.distribuidora.features.clients.ClientEntity;
import com.Districto_Tech.distribuidora.features.clients.ClientRepository;
import com.Districto_Tech.distribuidora.features.employees.EmployeeEntity;
import com.Districto_Tech.distribuidora.features.employees.EmployeeRepository;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderRequestDto;
import com.Districto_Tech.distribuidora.features.orders.dto.OrderResponseDto;
import com.Districto_Tech.distribuidora.features.orders_details.OrderDetailsModelMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class OrderModelMapper implements IModelMapper<OrderEntity, OrderResponseDto, OrderRequestDto> {

    private final ModelMapper modelMapper;
    private final OrderDetailsModelMapper orderDetailsModelMapper;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public OrderEntity toEntity(OrderRequestDto dto) {
        ClientEntity client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado."));

        EmployeeEntity employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado."));

        return OrderEntity.builder()
                .orderStatus(Status.PENDING)
                .orderDate(LocalDate.now())
                .client(client)
                .employee(employee)
                .build();
    }

    @Override
    public OrderResponseDto toDto(OrderEntity entity) {
        OrderResponseDto dto = modelMapper.map(entity, OrderResponseDto.class);

        if (entity.getClient() != null) {
            dto.setClientId(entity.getClient().getId());
            dto.setClientName(entity.getClient().getNameAndSurname());
        }
        if (entity.getEmployee() != null) {
            dto.setEmployeeId(entity.getEmployee().getIdEmployee());
            dto.setEmployeeName(entity.getEmployee().getName());
        }
        if (entity.getOrderDetailsList() != null) {
            dto.setItems(entity.getOrderDetailsList().stream()
                    .map(orderDetailsModelMapper::toDto).toList());

            double total = entity.getOrderDetailsList().stream()
                    .mapToDouble(d -> d.getHistoricalPrice() * d.getQuantity())
                    .sum();
            dto.setTotal(total);
        }
        return dto;
    }
}