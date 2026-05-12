package com.Districto_Tech.distribuidora.features.payment.dto;

import com.Districto_Tech.distribuidora.common.IMapperConvertGlobalObjects;
import com.Districto_Tech.distribuidora.features.payment.entity.PaymentEntity;
import com.Districto_Tech.distribuidora.features.shipping.dto.ShippingRequestDTO;
import com.Districto_Tech.distribuidora.features.shipping.dto.ShippingResponseDTO;
import com.Districto_Tech.distribuidora.features.shipping.entity.ShippingEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper implements IMapperConvertGlobalObjects<PaymentEntity, PaymentRequestDTO, PaymentResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PaymentResponseDTO toResponseDto(PaymentEntity entity) {
        return null;
    }

    @Override
    public PaymentRequestDTO toRequestDto(PaymentEntity entity) {
        return null;
    }

    @Override
    public PaymentEntity toEntityRS(PaymentResponseDTO responseDto) {
        return null;
    }

    @Override
    public PaymentEntity toEntityRQ(PaymentRequestDTO requestDto) {
        return null;
    }
}