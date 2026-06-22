package com.Districto_Tech.distribuidora.features.payments;

import com.Districto_Tech.distribuidora.common.exceptions.ResourceNotFoundException;
import com.Districto_Tech.distribuidora.features.orders.OrderEntity;
import com.Districto_Tech.distribuidora.features.orders.OrderRepository;
import com.Districto_Tech.distribuidora.features.payments.discount.DiscountCategory;
import com.Districto_Tech.distribuidora.features.payments.discount.DiscountType;
import com.Districto_Tech.distribuidora.features.payments.discount.DiscountTypeRepository;
import com.Districto_Tech.distribuidora.features.payments.dto.PaymentRequestDto;
import com.Districto_Tech.distribuidora.features.payments.dto.PaymentResponseDto;
import com.Districto_Tech.distribuidora.features.payments.methods.PaymentMethod;
import com.Districto_Tech.distribuidora.features.payments.methods.PaymentMethodRepository;
import com.Districto_Tech.distribuidora.features.payments.methods.PaymentMethodType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private PaymentMethodRepository paymentMethodRepository;
    @Mock
    private DiscountTypeRepository discountTypeRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private PaymentMapper paymentMapper;

    @InjectMocks
    private PaymentService paymentService;

    private Payment payment;
    private PaymentRequestDto requestDto;
    private PaymentResponseDto responseDto;
    private PaymentMethod paymentMethod;
    private DiscountType discountType;
    private OrderEntity order;

    @BeforeEach
    void setUp() {
        paymentMethod = PaymentMethod.builder().id(1L).name(PaymentMethodType.CASH).build();
        discountType = DiscountType.builder().id(1L).name(DiscountCategory.COMMON).percentage(0.0).build();
        order = OrderEntity.builder().id(1L).build();

        payment = Payment.builder()
                .id(1L)
                .amount(1250.0)
                .date(LocalDateTime.now())
                .paymentMethod(paymentMethod)
                .discountType(discountType)
                .order(order)
                .build();

        requestDto = PaymentRequestDto.builder()
                .amount(1250.0)
                .paymentMethodId(1L)
                .discountTypeId(1L)
                .orderId(1L)
                .build();

        responseDto = PaymentResponseDto.builder()
                .id(1L)
                .amount(1250.0)
                .paymentMethod("CASH")
                .discountType("COMMON")
                .discountPercentage(0.0)
                .orderId(1L)
                .build();
    }

    @Test
    void save_ShouldCreatePaymentSuccessfully() {
        when(paymentMethodRepository.findById(1L)).thenReturn(Optional.of(paymentMethod));
        when(discountTypeRepository.findById(1L)).thenReturn(Optional.of(discountType));
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);
        when(paymentMapper.toDto(any(Payment.class))).thenReturn(responseDto);

        PaymentResponseDto result = paymentService.save(requestDto);

        assertNotNull(result);
        assertEquals(1250.0, result.getAmount());
        verify(paymentRepository).save(any());
    }

    @Test
    void getAll_ShouldReturnAllPayments() {
        when(paymentRepository.findAll()).thenReturn(List.of(payment));
        when(paymentMapper.toDto(any(Payment.class))).thenReturn(responseDto);

        List<PaymentResponseDto> result = paymentService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void getById_WhenPaymentExists_ShouldReturnPayment() {
        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));
        when(paymentMapper.toDto(any(Payment.class))).thenReturn(responseDto);

        PaymentResponseDto result = paymentService.getById(1L);

        assertNotNull(result);
        assertEquals(1250.0, result.getAmount());
    }

    @Test
    void getById_WhenPaymentNotExists_ShouldThrowException() {
        when(paymentRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> paymentService.getById(99L));
    }

    @Test
    void deleteById_ShouldDeletePayment() {
        doNothing().when(paymentRepository).deleteById(1L);

        assertDoesNotThrow(() -> paymentService.deleteById(1L));

        verify(paymentRepository).deleteById(1L);
    }
}