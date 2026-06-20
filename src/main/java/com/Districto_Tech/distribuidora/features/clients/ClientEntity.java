package com.Districto_Tech.distribuidora.features.clients;
import com.Districto_Tech.distribuidora.features.orders.OrderEntity;
import com.Districto_Tech.distribuidora.features.users.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "clients")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nameAndSurname;

    @Column(nullable = false, unique = true)
    private String DNI;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String address;

    @Column(nullable = false)
    private boolean isVip;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CountStatus countStatus;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true )
    private List<OrderEntity> orders = new ArrayList<>();

}
