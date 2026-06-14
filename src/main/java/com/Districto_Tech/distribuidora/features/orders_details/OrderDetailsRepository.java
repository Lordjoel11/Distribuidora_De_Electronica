package com.Districto_Tech.distribuidora.features.orders_details;

import com.Districto_Tech.distribuidora.features.orders.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderDetailsRepository extends JpaRepository <OrderDetails, Long> {


    Optional<OrderDetails> findFirstByOrderEntity_OrderCode(UUID orderCode);

    List<OrderDetails> findByOrderEntity_OrderCode(UUID orderCode);
}
