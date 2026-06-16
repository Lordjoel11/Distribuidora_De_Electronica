package com.Districto_Tech.distribuidora.features.shipping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ShippingRepository extends JpaRepository<ShippingEntity, Long> {

    Optional<ShippingEntity> findById(Long id);


}
