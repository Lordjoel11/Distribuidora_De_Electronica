package com.Districto_Tech.distribuidora.features.products.repository;


import com.Districto_Tech.distribuidora.features.products.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductsEntity, Long> {

    Optional<ProductsEntity> findByPublicId(String publicId);

    boolean existsByPublicId(String publicId);
}