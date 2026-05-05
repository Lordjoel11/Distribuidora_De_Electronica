package com.Districto_Tech.distribuidora.features.users.services;

import com.Districto_Tech.distribuidora.common.IEntitiesService;
import com.Districto_Tech.distribuidora.features.users.dto.UserMappers;
import com.Districto_Tech.distribuidora.features.users.dto.UserRequestDto;
import com.Districto_Tech.distribuidora.features.users.dto.UserResponseDto;
import com.Districto_Tech.distribuidora.features.users.entities.UserEntity;
import com.Districto_Tech.distribuidora.features.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IEntitiesService<UserEntity, UserRequestDto, UserResponseDto> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserMappers userMappers;



    @Override
    public UserRequestDto createDtoRQ(UserEntity entity) {
        return null;
    }

    @Override
    public UserResponseDto createDtoRS(UserEntity entity) {
        return null;
    }

    @Override
    public List<UserResponseDto> listDtoRQ() {
        return List.of();
    }

    @Override
    public List<UserResponseDto> listDtoRS() {
        return List.of();
    }

    @Override
    public UserRequestDto getByPublicIdDtoRQ(String publicId) {
        return null;
    }

    @Override
    public UserResponseDto getByIdPublicDtoRS(String publicId) {
        return null;
    }

    @Override
    public UserRequestDto updateDtoRQ(String publicId, UserRequestDto dtoRQ) {
        return null;
    }

    @Override
    public UserResponseDto updateDtoRS(String publicId, UserResponseDto dtoRS) {
        return null;
    }


    @Override
    public void delete(String publicId) {

    }
}
