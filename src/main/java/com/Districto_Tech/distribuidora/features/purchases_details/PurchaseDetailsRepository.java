package com.Districto_Tech.distribuidora.features.purchases_details;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseDetailsRepository extends JpaRepository<PurchaseDetails, Long> {
    List<PurchaseDetails> findByPurchase_Id(Long purchaseId);
}