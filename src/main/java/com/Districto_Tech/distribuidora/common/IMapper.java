package com.Districto_Tech.distribuidora.common;

public interface IMapper <Entity, ResponseDto, RequestDto>{

Entity toEntity(RequestDto requestDto);
ResponseDto toDto(Entity entity);
}
