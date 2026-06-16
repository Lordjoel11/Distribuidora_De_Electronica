package com.Districto_Tech.distribuidora.features.shipping;

import com.Districto_Tech.distribuidora.features.users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShippingRepository extends JpaRepository<ShippingEntity, UUID> {

    @Override
    Optional<ShippingEntity> findById(UUID id);
    boolean existsById (UUID id);

}
