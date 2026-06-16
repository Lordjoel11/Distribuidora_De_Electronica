package com.Districto_Tech.distribuidora.features.employees;

import com.Districto_Tech.distribuidora.features.employees.misc.Role;
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
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmployee;

    @Column(name = "first_name", length = 25, nullable = false)
    private String name;

    @Column(name = "surname", length = 25, nullable = false)
    private String surname;

    @Column(length = 13, nullable = false, unique = true)
    private String CUIL;

    @Column(length = 10, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


}
