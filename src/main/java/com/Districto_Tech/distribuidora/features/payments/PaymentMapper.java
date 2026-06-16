package com.Districto_Tech.distribuidora.features.payments;

import com.Districto_Tech.distribuidora.common.IModelMapper;
import com.Districto_Tech.distribuidora.common.config.MapperConfig;
import com.Districto_Tech.distribuidora.features.payments.dto.PaymentRequestDto;
import com.Districto_Tech.distribuidora.features.payments.dto.PaymentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PaymentMapper implements IModelMapper<Payment, PaymentResponseDto, PaymentRequestDto> {

    private final MapperConfig mapperConfig;

    @Override
    public Payment toEntity(PaymentRequestDto dto) {
        return mapperConfig.modelMapper().map(dto, Payment.class);
    }

    @Override
    public PaymentResponseDto toDto(Payment payment) {
        PaymentResponseDto dto = mapperConfig.modelMapper().map(payment, PaymentResponseDto.class);
        dto.setPaymentMethod(payment.getPaymentMethod().getName().name());
        dto.setDiscountType(payment.getDiscountType().getName().name());
        dto.setDiscountPercentage(payment.getDiscountType().getPercentage());
        dto.setOrderId(payment.getOrder().getId());
        return dto;
    }
}
