package com.Districto_Tech.distribuidora.features.orders_details;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders_details")

public class OrderDetails {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany (mappedBy = "orders_details")
    private List<OrderDetails> orderDetailsList;

    @Column (name = "description")
    private String orderDescription;

    @Column (name = "quantity")
    private Integer orderQuantity;

    @Column (name = "historical_price")
    private Double historicalPrice;
}
