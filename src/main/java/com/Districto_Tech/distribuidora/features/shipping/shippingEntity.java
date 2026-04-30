package com.Districto_Tech.distribuidora.features.shipping;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "shipping")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class shippingEntity {

    shippingStatus status;
    int idShipping;
    LocalDate date;



    }

