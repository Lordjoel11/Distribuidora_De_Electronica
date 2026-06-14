package com.Districto_Tech.distribuidora.features.users;

import com.Districto_Tech.distribuidora.common.IMapper;
import com.Districto_Tech.distribuidora.common.MapperConfig;
import com.Districto_Tech.distribuidora.features.users.dto.UserRequestDto;
import com.Districto_Tech.distribuidora.features.users.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements IMapper<UserEntity, UserResponseDto, UserRequestDto> {

    private MapperConfig mapperConfig;

    @Override
    public UserEntity toEntity(UserRequestDto userRequestDto) {
        return mapperConfig.modelMapper().map(userRequestDto, UserEntity.class);
    }

    @Override
    public UserResponseDto toDto(UserEntity userEntity) {
        return mapperConfig.modelMapper().map(userEntity, UserResponseDto.class);

    }
}
