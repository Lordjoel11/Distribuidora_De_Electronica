package com.Districto_Tech.distribuidora.features.users;

import com.Districto_Tech.distribuidora.features.users.dto.AdminUserRequestDto;
import com.Districto_Tech.distribuidora.features.users.dto.UserRequestDto;
import com.Districto_Tech.distribuidora.features.users.dto.UserResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;


    @PostMapping
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody AdminUserRequestDto request) {
        UserResponseDto newUser = userService.saveByAdmin(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id) {
        UserResponseDto user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDto request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/approve")
    public ResponseEntity<UserResponseDto> approve(@PathVariable Long id) {
        return ResponseEntity.ok(userService.approve(id));
    }








}
