package com.Districto_Tech.distribuidora.common;

public interface IMapperConvertGlobalObjects<E, RQ, RS>{

    RS toResponseDto(E entity);
    RQ toRequestDto(E entity);
    E toEntityRS (RS responseDto);
    E toEntityRQ (RQ requestDto);

}
