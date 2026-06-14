package com.Districto_Tech.distribuidora.features.products;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Integer stock;

    private Double unitPrice;

    @Enumerated(EnumType.STRING)
    private Category category;
}