package com.Districto_Tech.distribuidora.features.users;

import com.Districto_Tech.distribuidora.common.IService;
import com.Districto_Tech.distribuidora.common.exceptions.InvalidDataException;
import com.Districto_Tech.distribuidora.common.exceptions.ResourceNotFoundException;
import com.Districto_Tech.distribuidora.features.users.dto.AdminUserRequestDto;
import com.Districto_Tech.distribuidora.features.users.dto.UserRequestDto;
import com.Districto_Tech.distribuidora.features.users.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IService<UserRequestDto, UserResponseDto, Long> {

    private final UserRepository userRepository;
    private final UserModelMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto save(UserRequestDto dto) {
        throw new UnsupportedOperationException("Use /api/users con AdminUserRequestDto o /api/v1/auth/register.");
    }

    public UserResponseDto saveByAdmin(AdminUserRequestDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new InvalidDataException("El email ya está registrado: " + dto.getEmail());
        }

        UserEntity userEntity = UserEntity.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roleType(dto.getRoleType())
                .approvalStatus(ApprovalStatus.APPROVED)
                .build();

        userEntity = userRepository.save(userEntity);
        return userMapper.toDto(userEntity);
    }

    @Override
    public List<UserResponseDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));

        return userMapper.toDto(userEntity);
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto dto) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede actualizar. El Usuario no existe.");
        }
        UserEntity updatedUser = userRepository.save(userMapper.toEntity(dto));
        updatedUser.setId(id);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. El Usuario no existe.");
        }
        userRepository.deleteById(id);
    }

    public UserResponseDto approve(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));

        if (userEntity.getRoleType() != RoleType.CLIENT) {
            throw new InvalidDataException("Solo los usuarios CLIENT requieren aprobación.");
        }

        userEntity.setApprovalStatus(ApprovalStatus.APPROVED);
        userRepository.save(userEntity);
        return userMapper.toDto(userEntity);
    }

    public List<UserResponseDto> getByApprovalStatus(ApprovalStatus status) {
        return userRepository.findByApprovalStatus(status)
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
}