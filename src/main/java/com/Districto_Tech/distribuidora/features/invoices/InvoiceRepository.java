package com.Districto_Tech.distribuidora.features.invoices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {
    Optional<InvoiceEntity> findByOrder_Id(Long orderId);
    List<InvoiceEntity> findByOrder_Client_User_Id(Long userId);
}