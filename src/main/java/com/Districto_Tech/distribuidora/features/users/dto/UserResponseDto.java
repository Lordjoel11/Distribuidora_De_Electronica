package com.Districto_Tech.distribuidora.features.users.dto;

import com.Districto_Tech.distribuidora.features.users.ApprovalStatus;
import com.Districto_Tech.distribuidora.features.users.RoleType;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private Long id;
    private UUID publicId;
    private String email;
    private RoleType roleType;
    private ApprovalStatus approvalStatus;
}