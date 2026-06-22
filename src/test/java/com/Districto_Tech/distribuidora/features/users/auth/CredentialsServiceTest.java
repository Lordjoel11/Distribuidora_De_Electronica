package com.Districto_Tech.distribuidora.features.users.auth;

import com.Districto_Tech.distribuidora.common.config.JwtService;
import com.Districto_Tech.distribuidora.features.users.RoleType;
import com.Districto_Tech.distribuidora.features.users.UserEntity;
import com.Districto_Tech.distribuidora.features.users.UserRepository;
import com.Districto_Tech.distribuidora.features.users.dto.UserRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CredentialsServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private CredentialsService credentialsService;

    private UserEntity userEntity;
    private UserRequestDto userRequestDto;
    private CredentialsRequest credentialsRequest;
    private CredentialsResponse credentialsResponse;

    @BeforeEach
    void setUp() {
        userEntity = UserEntity.builder()
                .id(1L)
                .email("admin@distribuidora.com")
                .password("encodedPass")
                .roleType(RoleType.ADMIN)
                .publicId(UUID.randomUUID())
                .build();

        userRequestDto = UserRequestDto.builder()
                .email("admin@distribuidora.com")
                .password("123456")
                .roleType(RoleType.CLIENT)
                .build();

        credentialsRequest = new CredentialsRequest();
        credentialsRequest.setUsername("admin@distribuidora.com");
        credentialsRequest.setPassword("123456");

        credentialsResponse = CredentialsResponse.builder()
                .token("jwt.token.example")
                .build();
    }

    @Test
    void authenticate_ShouldLoginSuccessfully() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(userRepository.findByEmail(credentialsRequest.getUsername())).thenReturn(Optional.of(userEntity));
        when(jwtService.generateToken(any(UserEntity.class))).thenReturn("jwt.token.example");

        CredentialsResponse result = credentialsService.authenticate(credentialsRequest);

        assertNotNull(result);
        assertEquals("jwt.token.example", result.getToken());
        verify(authenticationManager).authenticate(any());
        verify(jwtService).generateToken(any());
    }

    @Test
    void register_ShouldRegisterUserSuccessfully() {
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        when(jwtService.generateToken(any(UserEntity.class))).thenReturn("jwt.token.example");

        CredentialsResponse result = credentialsService.register(userRequestDto);

        assertNotNull(result);
        assertNotNull(result.getToken());
        verify(userRepository).save(any(UserEntity.class));
        verify(jwtService).generateToken(any());
    }

    @Test
    void authenticate_WhenUserNotFound_ShouldThrowException() {
        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> credentialsService.authenticate(credentialsRequest));
    }
}