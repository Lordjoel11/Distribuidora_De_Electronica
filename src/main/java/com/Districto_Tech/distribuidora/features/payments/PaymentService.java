package com.Districto_Tech.distribuidora.features.payments;

import com.Districto_Tech.distribuidora.common.IService;
import com.Districto_Tech.distribuidora.common.exceptions.ResourceNotFoundException;
import com.Districto_Tech.distribuidora.features.orders.OrderEntity;
import com.Districto_Tech.distribuidora.features.orders.OrderRepository;
import com.Districto_Tech.distribuidora.features.payments.discount.DiscountType;
import com.Districto_Tech.distribuidora.features.payments.discount.DiscountTypeRepository;
import com.Districto_Tech.distribuidora.features.payments.dto.PaymentRequestDto;
import com.Districto_Tech.distribuidora.features.payments.dto.PaymentResponseDto;
import com.Districto_Tech.distribuidora.features.payments.methods.PaymentMethod;
import com.Districto_Tech.distribuidora.features.payments.methods.PaymentMethodRepository;
import com.Districto_Tech.distribuidora.features.users.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService implements IService<PaymentRequestDto, PaymentResponseDto, Long> {

    private final PaymentRepository paymentRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final DiscountTypeRepository discountTypeRepository;
    private final OrderRepository orderRepository;

    @Override
    public PaymentResponseDto save(PaymentRequestDto request) {
        PaymentMethod method = paymentMethodRepository.findById(request.getPaymentMethodId())
                .orElseThrow(() -> new ResourceNotFoundException("Método de pago no encontrado."));

        DiscountType discount = discountTypeRepository.findById(request.getDiscountTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de descuento no encontrado."));

        OrderEntity order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado."));

        Payment payment = Payment.builder()
                .amount(request.getAmount())
                .date(LocalDateTime.now())
                .paymentMethod(method)
                .discountType(discount)
                .order(order)
                .build();

        return toResponse(paymentRepository.save(payment));
    }

    @Override
    public List<PaymentResponseDto> getAll() {
        return paymentRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getById(Long id) {
        return toResponse(paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado.")));
    }

    @Override
    public PaymentResponseDto update(Long id, PaymentRequestDto request) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado."));

        PaymentMethod method = paymentMethodRepository.findById(request.getPaymentMethodId())
                .orElseThrow(() -> new ResourceNotFoundException("Método de pago no encontrado."));

        DiscountType discount = discountTypeRepository.findById(request.getDiscountTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de descuento no encontrado."));

        OrderEntity order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado."));

        payment.setAmount(request.getAmount());
        payment.setPaymentMethod(method);
        payment.setDiscountType(discount);
        payment.setOrder(order);

        return toResponse(paymentRepository.save(payment));
    }

    @Override
    public void deleteById(Long id) {
        paymentRepository.deleteById(id);
    }

    private PaymentResponseDto toResponse(Payment payment) {
        return PaymentResponseDto.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .date(payment.getDate())
                .paymentMethod(payment.getPaymentMethod().getName().name())
                .discountType(payment.getDiscountType().getName().name())
                .discountPercentage(payment.getDiscountType().getPercentage())
                .orderId(payment.getOrder().getId())
                .build();
    }
}