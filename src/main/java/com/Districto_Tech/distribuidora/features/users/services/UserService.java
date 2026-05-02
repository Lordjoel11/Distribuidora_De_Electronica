package com.Districto_Tech.distribuidora.features.users.services;

import com.Districto_Tech.distribuidora.features.users.dto.UserResponseDto;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Override
    public UserResponseDto create(UserResponseDto dto) {
        return null;
    }

    @Override
    public List<UserResponseDto> listDTO() {
        return List.of();
    }

    @Override
    public UserResponseDto getById(String publicId) {
        return null;
    }

    @Override
    public UserResponseDto update(String publicId, UserResponseDto dto) {
        return null;
    }

    @Override
    public void delete(String publicId) {

    }
}
