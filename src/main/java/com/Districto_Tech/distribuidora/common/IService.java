package com.Districto_Tech.distribuidora.common;


import com.Districto_Tech.distribuidora.features.users.dto.UserResponseDto;

import java.util.List;

public interface IService<RQS,RPS,ID> {
    RPS save(RQS request);
    List<RPS> getAll();
    RPS getById(ID id);

    RPS update(ID id, RQS request);
    void deleteById(ID id);
}


