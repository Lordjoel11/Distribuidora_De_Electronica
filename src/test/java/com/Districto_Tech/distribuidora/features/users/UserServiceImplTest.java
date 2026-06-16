package com.Districto_Tech.distribuidora.features.users;

import com.Districto_Tech.distribuidora.common.exceptions.ResourceNotFoundException;
import com.Districto_Tech.distribuidora.features.users.dto.UserRequestDto;
import com.Districto_Tech.distribuidora.features.users.dto.UserResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserModelMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private UserEntity userEntity;
    private UserRequestDto requestDto;
    private UserResponseDto responseDto;

    @BeforeEach
    void setUp() {
        userEntity = UserEntity.builder()
                .id(1L)
                .email("test@distribuidora.com")
                .roleType(RoleType.CLIENT)
                .publicId(UUID.randomUUID())
                .build();

        requestDto = UserRequestDto.builder()
                .email("test@distribuidora.com")
                .password("123456")
                .roleType(RoleType.CLIENT)
                .build();

        responseDto = UserResponseDto.builder()
                .publicId(userEntity.getPublicId())
                .email("test@distribuidora.com")
                .roleType(RoleType.CLIENT)
                .build();
    }

    @Test
    void save_ShouldRegisterUserSuccessfully() {
        when(userRepository.existsByEmail(requestDto.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(requestDto.getPassword())).thenReturn("encodedPassword");
        when(userMapper.toEntity(any(UserRequestDto.class))).thenReturn(userEntity);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        when(userMapper.toDto(any(UserEntity.class))).thenReturn(responseDto);

        UserResponseDto result = userService.save(requestDto);

        assertNotNull(result);
        assertEquals("test@distribuidora.com", result.getEmail());
        verify(userRepository).save(any());
        verify(passwordEncoder).encode(anyString());
    }

    @Test
    void save_WhenEmailExists_ShouldThrowException() {
        when(userRepository.existsByEmail(requestDto.getEmail())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> userService.save(requestDto));
    }

    @Test
    void getAll_ShouldReturnAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(userEntity));
        when(userMapper.toDto(any(UserEntity.class))).thenReturn(responseDto);

        List<UserResponseDto> result = userService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void getById_WhenUserExists_ShouldReturnUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        when(userMapper.toDto(any(UserEntity.class))).thenReturn(responseDto);

        UserResponseDto result = userService.getById(1L);

        assertNotNull(result);
        assertEquals("test@distribuidora.com", result.getEmail());
    }

    @Test
    void getById_WhenUserNotExists_ShouldThrowException() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.getById(99L));
    }

    @Test
    void deleteById_WhenUserExists_ShouldDeleteSuccessfully() {
        when(userRepository.existsById(1L)).thenReturn(true);
        doNothing().when(userRepository).deleteById(1L);

        assertDoesNotThrow(() -> userService.deleteById(1L));

        verify(userRepository).deleteById(1L);
    }
}