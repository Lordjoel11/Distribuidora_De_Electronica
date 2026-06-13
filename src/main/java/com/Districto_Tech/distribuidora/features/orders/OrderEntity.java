package com.Districto_Tech.distribuidora.features.orders;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table (name = "order")

public class OrderEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "order_date")
    private LocalDate orderDate;

    @Column (name = "order_status")
    private Status orderStatus;

//    @ManyToOne (fetch = FetchType.LAZY)
//    @JoinColumn (name = "client_id")
//    private ClientEntity clientId;
//
//    @ManyToOne (fetch = FetchType.LAZY)
//    @JoinColumn (name = "employee_id")
//    private EmployeeEntity employeeId;
}
