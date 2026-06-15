package com.Districto_Tech.distribuidora.common;

public interface IModelMapper<Entity, ResponseDto, RequestDto>{

    Entity toEntity(RequestDto requestDto);
    ResponseDto toDto(Entity entity);

}
