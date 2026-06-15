package com.Districto_Tech.distribuidora.features.users.dto;

import com.Districto_Tech.distribuidora.features.users.RoleType;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private UUID publicId;
    private String email;
    private RoleType roleType;
}
