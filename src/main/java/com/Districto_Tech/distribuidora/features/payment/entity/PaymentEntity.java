package com.Districto_Tech.distribuidora.features.payment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)

    private String publicId;

    private PaymentType paymentType;

    private int idPayment;

    private double amount;

    private LocalDate date;

    @PrePersist
    public void generateUuid() {
        this.publicId = java.util.UUID.randomUUID().toString();
}
}