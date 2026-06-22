package com.Districto_Tech.distribuidora.features.users.auth;


import com.Districto_Tech.distribuidora.features.users.dto.UserRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "Autenticación y Registro")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class CredentialsController {

    private final CredentialsService service;

    @Operation(summary = "Login de usuario", description = "Autentica usuario y devuelve JWT")
    @ApiResponse(responseCode = "200", description = "Login exitoso")
    @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    @PostMapping("/login")
    public ResponseEntity<CredentialsResponse> authenticate(
            @RequestBody CredentialsRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @Operation(summary = "Registro de cliente", description = "Registra un nuevo cliente")
    @ApiResponse(responseCode = "201", description = "Usuario registrado correctamente")
    @PostMapping("/register")
    public ResponseEntity<CredentialsResponse> register(@RequestBody UserRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.register(request));
    }


    @RestController
    public class TestController {

        @GetMapping("/test")
        public String test() {
            return "OK";
        }
    }
}