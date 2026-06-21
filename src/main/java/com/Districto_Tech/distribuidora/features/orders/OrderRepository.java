package com.Districto_Tech.distribuidora.features.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> findByOrderCode(UUID orderCode);
    List<OrderEntity> findByClient_Id(Long clientId);
    List<OrderEntity> findByOrderStatus(Status orderStatus);
    List<OrderEntity> findByClient_IdAndOrderStatus(Long clientId, Status status);
}