package com.Districto_Tech.distribuidora.features.payments.discount;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "discount_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private DiscountCategory name;

    private Double percentage;
}