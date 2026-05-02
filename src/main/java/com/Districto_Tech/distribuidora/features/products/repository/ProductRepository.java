package com.Districto_Tech.distribuidora.features.products.repository;


import com.Districto_Tech.distribuidora.features.products.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByPublicId(String publicId);

    boolean existsByPublicId(String publicId);
}