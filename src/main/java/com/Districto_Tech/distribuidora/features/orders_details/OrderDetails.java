package com.Districto_Tech.distribuidora.features.orders_details;

import com.Districto_Tech.distribuidora.features.orders.OrderEntity;
import com.Districto_Tech.distribuidora.features.products.Product;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders_details")

public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_id", unique = true)
    private UUID publicId;

    @Column(name = "description")
    private String orderDescription;

    @Column(name = "quantity")
    private Integer orderQuantity;

    @Column(name = "historical_price")
    private Double historicalPrice;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product productEntity;


    @PrePersist
    protected void generateRandomCode() {
        if(this.publicId == null) this.publicId = UUID.randomUUID();
    }

}
