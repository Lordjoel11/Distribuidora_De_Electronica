package com.Districto_Tech.distribuidora.features.orders;

import com.Districto_Tech.distribuidora.features.clients.ClientEntity;
import com.Districto_Tech.distribuidora.features.employees.EmployeeEntity;
import com.Districto_Tech.distribuidora.features.orders_details.OrderDetails;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders")

public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false ,unique = true)
    private Long id;

    @Column(name = "order_code", unique = true)
    private UUID orderCode;


    @Column(name = "order_date")
    private LocalDate orderDate;


    @Column(name = "order_status")
    @Enumerated (EnumType.STRING)
    private Status orderStatus;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "client")
    private ClientEntity clientId;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "employee")
    private EmployeeEntity employeeId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntity> orderEntities = new ArrayList<>();


    @PrePersist

    protected void generateRandomCode() {

        this.orderCode = UUID.randomUUID();

    }

    @OneToMany(mappedBy = "orderEntity", fetch = FetchType.LAZY)
    private List<OrderDetails> orderDetailsList;
}
