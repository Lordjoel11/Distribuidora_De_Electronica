package com.Districto_Tech.distribuidora.features.invoices;

import com.Districto_Tech.distribuidora.common.exceptions.ResourceAlrredyExistException;
import com.Districto_Tech.distribuidora.common.exceptions.ResourceNotFoundException;
import com.Districto_Tech.distribuidora.features.invoices.dto.InvoiceRequestDto;
import com.Districto_Tech.distribuidora.features.invoices.dto.InvoiceResponseDto;
import com.Districto_Tech.distribuidora.features.orders.OrderEntity;
import com.Districto_Tech.distribuidora.features.orders.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final OrderRepository orderRepository;
    private final InvoiceMapper invoiceMapper;

    public InvoiceResponseDto save(InvoiceRequestDto dto) {
        OrderEntity order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado."));

        if (invoiceRepository.findByOrder_Id(dto.getOrderId()).isPresent()) {
            throw new ResourceAlrredyExistException("Ya tiene factura este pedido.");
        }

        InvoiceEntity invoice = InvoiceEntity.builder()
                .invoiceType(dto.getInvoiceType())
                .date(LocalDateTime.now())
                .total(dto.getTotal())
                .order(order)
                .build();

        return invoiceMapper.toDto(invoiceRepository.save(invoice));
    }

    public List<InvoiceResponseDto> getAll() {
        return invoiceRepository.findAll().stream()
                .map(invoiceMapper::toDto).toList();
    }

    public InvoiceResponseDto getById(Long id) {
        return invoiceMapper.toDto(invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada.")));
    }

    public InvoiceResponseDto getByOrderId(Long orderId) {
        return invoiceMapper.toDto(invoiceRepository.findByOrder_Id(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada.")));
    }

    public void delete(Long id) {
        if (!invoiceRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. El Recurso no existe.");
        }
        invoiceRepository.deleteById(id);
    }

    public List<InvoiceResponseDto> getMyInvoices(Long currentUserId) {
        return invoiceRepository.findByOrder_Client_User_Id(currentUserId)
                .stream()
                .map(invoiceMapper::toDto)
                .toList();
    }

}