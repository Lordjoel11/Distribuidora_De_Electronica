package com.Districto_Tech.distribuidora.features.users.repositories;


import com.Districto_Tech.distribuidora.features.users.entities.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findById (Long id);
}
