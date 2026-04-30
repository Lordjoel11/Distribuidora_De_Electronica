package com.Districto_Tech.distribuidora.features.users.Entities;

import com.Districto_Tech.distribuidora.features.users.rolType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

@Table(name = "users")

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String password;
    private rolType rolType;
}
