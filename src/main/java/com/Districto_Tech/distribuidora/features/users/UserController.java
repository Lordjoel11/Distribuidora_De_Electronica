package com.Districto_Tech.distribuidora.features.users;

import com.Districto_Tech.distribuidora.features.users.dto.AdminUserRequestDto;
import com.Districto_Tech.distribuidora.features.users.dto.UserRequestDto;
import com.Districto_Tech.distribuidora.features.users.dto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "Gestión de usuarios (Admin)")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @Operation(summary = "Crear usuario (solo Admin)", description = "Crea un usuario con rol específico")
    @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente")
    @PostMapping
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody AdminUserRequestDto request) {
        UserResponseDto newUser = userService.saveByAdmin(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @Operation(summary = "Listar todos los usuarios")
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @Operation(summary = "Obtener usuario por ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id) {
        UserResponseDto user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Actualizar Usuario")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDto request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.update(id, request));
    }

    @Operation(summary = "Eliminar usuario por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Aprobar usuario cliente")
    @PatchMapping("/{id}/approve")
    public ResponseEntity<UserResponseDto> approve(@PathVariable Long id) {
        return ResponseEntity.ok(userService.approve(id));
    }

    @Operation(summary = "Listar clientes en Pendientes")
    @GetMapping("/pending")
    public ResponseEntity<List<UserResponseDto>> getPendingUsers() {
        return ResponseEntity.ok(userService.getByApprovalStatus(ApprovalStatus.PENDING));
    }






}
