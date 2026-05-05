package com.Districto_Tech.distribuidora.features.users.dto;

import com.Districto_Tech.distribuidora.common.IMapperConvertGlobalObjects;
import com.Districto_Tech.distribuidora.features.users.entities.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMappers implements IMapperConvertGlobalObjects<UserEntity, UserRequestDto, UserResponseDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserEntity toEntityRQ(UserRequestDto requestDto) {
        return modelMapper.map(requestDto, UserEntity.class);
    }

    @Override
    public UserEntity toEntityRS(UserResponseDto responseDto) {
        return modelMapper.map(responseDto, UserEntity.class);
    }
    // -------------------------------------------------------
    @Override
    public UserResponseDto toResponseDto(UserEntity entity) {
        return modelMapper.map(entity, UserResponseDto.class);
    }

    @Override
    public UserRequestDto toRequestDto(UserEntity entity) {
        return modelMapper.map(entity, UserRequestDto.class);
    }

}
