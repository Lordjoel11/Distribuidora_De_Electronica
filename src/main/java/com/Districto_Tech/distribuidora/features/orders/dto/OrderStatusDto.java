package com.Districto_Tech.distribuidora.features.orders.dto;

import com.Districto_Tech.distribuidora.features.orders.Status;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class OrderStatusDto {

    @NotNull(message = "El estado no puede ser nulo.")
    private Status newStatus;
}