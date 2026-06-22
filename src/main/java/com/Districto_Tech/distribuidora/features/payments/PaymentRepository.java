package com.Districto_Tech.distribuidora.features.payments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByOrder_Id(Long orderId);
    List<Payment> findByOrder_Client_User_Id(Long userId);
}