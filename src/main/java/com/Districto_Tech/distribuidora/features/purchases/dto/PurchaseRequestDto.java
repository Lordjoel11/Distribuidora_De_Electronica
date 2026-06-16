package com.Districto_Tech.distribuidora.features.purchases.dto;

import com.Districto_Tech.distribuidora.features.purchases_details.dto.PurchaseDetailsRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseRequestDto {

    @NotNull(message = "Este campo no puede ser nulo.")
    private Long supplierId;

    @Valid
    @NotEmpty(message = "Este campo no puede estar vacio.")
    private List<PurchaseDetailsRequestDto> purchaseDetails;
}