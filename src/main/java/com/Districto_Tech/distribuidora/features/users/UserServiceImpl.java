package com.Districto_Tech.distribuidora.features.users;

import com.Districto_Tech.distribuidora.common.IService;
import com.Districto_Tech.distribuidora.common.exceptions.ResourceNotFoundException;
import com.Districto_Tech.distribuidora.features.users.dto.UserRequestDto;
import com.Districto_Tech.distribuidora.features.users.dto.UserResponseDto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IService<UserRequestDto, UserResponseDto, Long> {

    private final UserRepository userRepository;
    private final UserModelMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserModelMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDto save(UserRequestDto dto) {
        UserEntity userEntity = userRepository.save(userMapper.toEntity(dto));

        if (userEntity.getId() != null && userRepository.existsById(userEntity.getId())) {
            throw new ResourceNotFoundException("Usuario no encontrado de Gmail: " + dto.getEmail());
        }

        UserEntity savedUser = userRepository.save(userEntity);
        return userMapper.toDto(savedUser);
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