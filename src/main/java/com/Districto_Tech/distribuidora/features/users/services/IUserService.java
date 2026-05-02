package com.Districto_Tech.distribuidora.features.users.services;

import com.Districto_Tech.distribuidora.features.products.dto.ProductDTO;
import com.Districto_Tech.distribuidora.features.users.dto.UserResponseDto;

import java.util.List;

public interface IUserService {

    UserResponseDto create(UserResponseDto dto);

    List<UserResponseDto> listDTO();

    UserResponseDto getById(String publicId);

    UserResponseDto update(String publicId, UserResponseDto dto);

    void delete(String publicId);

}
