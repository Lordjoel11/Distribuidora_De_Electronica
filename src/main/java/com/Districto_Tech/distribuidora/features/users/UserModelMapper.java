package com.Districto_Tech.distribuidora.features.users;

import com.Districto_Tech.distribuidora.common.IModelMapper;
import com.Districto_Tech.distribuidora.common.config.MapperConfig;
import com.Districto_Tech.distribuidora.features.users.dto.UserRequestDto;
import com.Districto_Tech.distribuidora.features.users.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserModelMapper implements IModelMapper<UserEntity, UserResponseDto, UserRequestDto> {

    private final MapperConfig mapperConfig;

    @Override
    public UserEntity toEntity(UserRequestDto userRequestDto) {
        return mapperConfig.modelMapper().map(userRequestDto, UserEntity.class);
    }

    @Override
    public UserResponseDto toDto(UserEntity userEntity) {
        return mapperConfig.modelMapper().map(userEntity, UserResponseDto.class);

    }
}
