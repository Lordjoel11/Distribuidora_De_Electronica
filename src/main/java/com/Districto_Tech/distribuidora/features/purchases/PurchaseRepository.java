package com.Districto_Tech.distribuidora.features.purchases;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {
    List<PurchaseEntity> findBySupplierId(Long supplierId);
    List<PurchaseEntity> findByPurchaseStatus(PurchaseStatus status);
}