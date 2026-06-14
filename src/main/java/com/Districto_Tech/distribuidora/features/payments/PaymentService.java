package com.Districto_Tech.distribuidora.features.payments;

import com.Districto_Tech.distribuidora.common.IService;
import com.Districto_Tech.distribuidora.common.exceptions.NoEncontradoException;
import com.Districto_Tech.distribuidora.features.orders.OrderEntity;
import com.Districto_Tech.distribuidora.features.orders.OrderRepository;
import com.Districto_Tech.distribuidora.features.payments.discount.DiscountType;
import com.Districto_Tech.distribuidora.features.payments.discount.DiscountTypeRepository;
import com.Districto_Tech.distribuidora.features.payments.dto.PaymentRequestDto;
import com.Districto_Tech.distribuidora.features.payments.dto.PaymentResponseDto;
import com.Districto_Tech.distribuidora.features.payments.methods.PaymentMethod;
import com.Districto_Tech.distribuidora.features.payments.methods.PaymentMethodRepository;
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
                .orElseThrow(() -> new NoEncontradoException("Método de pago no encontrado."));

        DiscountType discount = discountTypeRepository.findById(request.getDiscountTypeId())
                .orElseThrow(() -> new NoEncontradoException("Tipo de descuento no encontrado."));

        OrderEntity order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new NoEncontradoException("Pedido no encontrado."));

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
    public PaymentResponseDto getById(Long id) {
        return toResponse(paymentRepository.findById(id)
                .orElseThrow(() -> new NoEncontradoException("Pago no encontrado.")));
    }

    @Override
    public PaymentResponseDto update(Long id, PaymentRequestDto request) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NoEncontradoException("Pago no encontrado."));

        PaymentMethod method = paymentMethodRepository.findById(request.getPaymentMethodId())
                .orElseThrow(() -> new NoEncontradoException("Método de pago no encontrado."));

        DiscountType discount = discountTypeRepository.findById(request.getDiscountTypeId())
                .orElseThrow(() -> new NoEncontradoException("Tipo de descuento no encontrado."));

        OrderEntity order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new NoEncontradoException("Pedido no encontrado."));

        payment.setAmount(request.getAmount());
        payment.setPaymentMethod(method);
        payment.setDiscountType(discount);
        payment.setOrder(order);

        return toResponse(paymentRepository.save(payment));
    }

    @Override
    public void delete(Long id) {
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