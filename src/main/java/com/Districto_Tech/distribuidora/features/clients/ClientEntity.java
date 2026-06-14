package com.Districto_Tech.distribuidora.features.clients;

import com.Districto_Tech.distribuidora.features.clients.Misc.TypeClient;
import com.Districto_Tech.distribuidora.features.users.UserEntity;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(nullable = false, unique = true)
    private String nameAndSurname;

    @Column(nullable = false, unique = true)
    private String DNI;

    @Column(nullable = false)
    private boolean isVip;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String address;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

}
