package com.Districto_Tech.distribuidora.features.orders;

import com.Districto_Tech.distribuidora.features.clients.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> findByOrderCode(UUID orderCode);
    List<OrderEntity> findByClient(ClientEntity client);
    List<OrderEntity> findByOrderStatus(Status orderStatus);
    List<OrderEntity> findByClientAndOrderStatus(ClientEntity client, Status status);
    List<OrderEntity> findByEmployeeIsNull();
}