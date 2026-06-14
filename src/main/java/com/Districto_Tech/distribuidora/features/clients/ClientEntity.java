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
@Table(name = "Clients")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IDClient;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(unique = true, nullable = false)
    private String DNI;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeClient typeClient;
    @Column(nullable = false)
    private Long phoneNumber;
    @Column(nullable = false)
    private String address;


    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity idUser;

}
