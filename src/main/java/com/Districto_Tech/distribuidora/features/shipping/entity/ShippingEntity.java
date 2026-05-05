package com.Districto_Tech.distribuidora.features.shipping.entity;
import java.time.LocalDate;

import com.Districto_Tech.distribuidora.features.clients.ClientEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

@Table(name = "shipping")

public class ShippingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String publicId;

    private ShippingStatus status;
    private int idShipping;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name= "id_cliente", nullable = false)
    private ClientEntity client;


    @PrePersist
    public void generateUuid() {
        this.publicId = java.util.UUID.randomUUID().toString();
    }
    }

