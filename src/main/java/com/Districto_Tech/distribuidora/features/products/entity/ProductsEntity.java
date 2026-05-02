package com.Districto_Tech.distribuidora.features.products.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String publicId;

    private String nombre;

    private double precio;

    private int stock;

    @PrePersist
    public void generateUuid() {
        this.publicId = java.util.UUID.randomUUID().toString();
    }
}