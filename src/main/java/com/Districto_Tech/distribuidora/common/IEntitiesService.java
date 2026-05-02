package com.Districto_Tech.distribuidora.common;

import com.Districto_Tech.distribuidora.features.users.dto.UserRequestDto;
import com.Districto_Tech.distribuidora.features.users.dto.UserResponseDto;

import java.util.List;

public interface IEntitiesService<E, RQ, RS> {

    RQ createDtoRQ(E entity);
    RS createDtoRS(E entity);

    List<UserResponseDto> listDtoRQ();
    List<UserResponseDto> listDtoRS();

    RQ getByPublicIdDtoRQ(String publicId);
    RS getByIdPublicDtoRS(String publicId);

    RQ updateDtoRQ(String publicId, UserRequestDto dtoRQ);
    RS updateDtoRS(String publicId, UserResponseDto dtoRS);

    void delete(String publicId);

}
