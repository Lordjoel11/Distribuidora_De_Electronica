package com.Districto_Tech.distribuidora.features.purchases.dto;
import com.Districto_Tech.distribuidora.features.purchases.PurchaseStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseStatusDto {

    @NotNull(message = "El nuevo estado es obligatorio")
    private PurchaseStatus newStatus;
}