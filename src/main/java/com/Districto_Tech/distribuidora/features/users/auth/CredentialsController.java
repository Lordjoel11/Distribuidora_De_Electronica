package com.Districto_Tech.distribuidora.features.users.auth;


import com.Districto_Tech.distribuidora.features.users.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class CredentialsController {

    private final CredentialsService service;

    @PostMapping("/login")
    public ResponseEntity<CredentialsResponse> authenticate(
            @RequestBody CredentialsRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<CredentialsResponse> register(@RequestBody UserRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.register(request));
    }
}