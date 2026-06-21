package com.Districto_Tech.distribuidora.features.users;

import com.Districto_Tech.distribuidora.features.clients.ClientEntity;
import com.Districto_Tech.distribuidora.features.employees.EmployeeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID publicId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

    @OneToOne(mappedBy = "user")
    private ClientEntity client;

    @OneToOne(mappedBy= "user")
    private EmployeeEntity employee;

    @PrePersist
    protected void generateRandomCode() {
        if(this.publicId == null) this.publicId = UUID.randomUUID();
        if(this.approvalStatus == null) this.approvalStatus = ApprovalStatus.PENDING;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + roleType.name()));
    }

    @Override
    public String getUsername() { return email; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() {
        // ADMIN y EMPLOYEE no necesitan aprobación; solo CLIENT
        if (roleType != RoleType.CLIENT) return true;
        return approvalStatus == ApprovalStatus.APPROVED;
    }
}