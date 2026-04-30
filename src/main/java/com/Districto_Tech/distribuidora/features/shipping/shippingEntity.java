package com.Districto_Tech.distribuidora.features.shipping;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

@Table(name = "shipping")

public class shippingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private shippingStatus status;
    private int idShipping;
    private LocalDate date;



    }

