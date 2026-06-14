package com.Districto_Tech.distribuidora.common;


import java.util.List;

public interface IService<E, ID> {
    E saveUser(E userEntity);
    List<E> getAllUsers();
    E getUserById (ID id);
    E update(ID id, E userEntity);
    void delete(ID id);
}


