package com.Districto_Tech.distribuidora.features.clients;

import com.Districto_Tech.distribuidora.features.users.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
    @Column(nullable = false, name = "Name")
    private String name;
    @Column(unique = true, nullable = false)
    @Size(min = 7, max = 8)
    private String DNI;
    @Column(nullable = false, name = "Is Vip")
    private Boolean isVip;
    @Column(nullable = false, name = "Phone Number")
    private Long phoneNumber;
    @Column(nullable = false, name = "Adress")
    private String address;

    private UserEntity idUser;

}
