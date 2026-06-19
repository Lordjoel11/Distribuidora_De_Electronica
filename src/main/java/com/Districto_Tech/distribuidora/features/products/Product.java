package com.Districto_Tech.distribuidora.features.products;

import com.Districto_Tech.distribuidora.features.orders_details.OrderDetailsEntity;
import com.Districto_Tech.distribuidora.features.suppliers.Supplier;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @Column (name = "name")
    private String name;

    @Column (name = "description")
    private String description;

    @Column (name = "stock")
    private Integer stock;

    @Column (name = "unit_price")
    private Double unitPrice;

    @Column (name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true )
    private List<OrderDetailsEntity> orderDetails = new ArrayList<>();
}