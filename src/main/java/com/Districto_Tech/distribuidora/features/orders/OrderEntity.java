package com.Districto_Tech.distribuidora.features.orders;

import com.Districto_Tech.distribuidora.features.clients.ClientEntity;
import com.Districto_Tech.distribuidora.features.employees.EmployeeEntity;
import com.Districto_Tech.distribuidora.features.orders_details.OrderDetails;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    private Long id;

    @Column(name = "order_code", unique = true)
    private UUID orderCode;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private Status orderStatus;

    @Column(name = "stock_discounted", nullable = false)
    private boolean stockDiscounted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetailsList;

    @PrePersist
    protected void generateRandomCode() {
        if (this.orderCode == null) this.orderCode = UUID.randomUUID();
    }
}