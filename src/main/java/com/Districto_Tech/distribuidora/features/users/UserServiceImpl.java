package com.Districto_Tech.distribuidora.features.users;

import com.Districto_Tech.distribuidora.common.IService;
import com.Districto_Tech.distribuidora.common.exceptions.ResourceNotFoundException;
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
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Usuario no encontrado de Gmail: " + dto.getEmail());
        }

        UserEntity userEntity = userMapper.toEntity(dto);
        userEntity.setRoleType(dto.getRoleType());
        userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));

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
}