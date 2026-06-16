package com.Districto_Tech.distribuidora.features.users.auth;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CredentialsResponse {
    private String token;
}