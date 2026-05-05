package com.Districto_Tech.distribuidora.features.users.entities;

import jakarta.persistence.*;
import lombok.*;

import java.rmi.server.UID;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

@Table(name = "users")

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false, columnDefinition = "BINARY(16)")
    private UUID publicID;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private rolType rolType;

    @PrePersist
    public void generateUuid(){
        if (this.publicID == null)
        {
            this.publicID = UUID.randomUUID();
        }
    }

}
