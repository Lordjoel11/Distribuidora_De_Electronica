package com.Districto_Tech.distribuidora.features.users.repositories;


import com.Districto_Tech.distribuidora.features.users.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
