package com.Districto_Tech.distribuidora.features.shipping;

import com.Districto_Tech.distribuidora.features.clients.ClientEntity;
import com.Districto_Tech.distribuidora.features.orders.OrderEntity;
import com.Districto_Tech.distribuidora.features.shipping.misc.State;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "Shipping")
public class ShippingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shippingId;

    @Column(length = 20, name = "Uuid")
    private UUID uuid;

    @Column(name = "SentDate")
    private LocalDate shippingSentDate;

    @Column(name = "DeliveredDate")
    private LocalDate shippingDeliveredDate;

    @Column(name = "State",  nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @OneToOne(mappedBy = "shippingEntity")
    @JoinColumn(name = "OrderID", nullable = false)
    private OrderEntity orderEntity;

}
