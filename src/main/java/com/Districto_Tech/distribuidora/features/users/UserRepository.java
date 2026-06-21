package com.Districto_Tech.distribuidora.features.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Override
    Optional<UserEntity> findById(Long id);
    Optional<UserEntity> findByEmail(String userEmail);
    boolean existsByEmail (String email);

}
