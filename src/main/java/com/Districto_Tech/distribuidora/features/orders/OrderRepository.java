package com.Districto_Tech.distribuidora.features.orders;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository <OrderEntity, Long> {
}
