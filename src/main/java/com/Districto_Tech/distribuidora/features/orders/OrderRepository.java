package com.Districto_Tech.distribuidora.features.orders;

import com.Districto_Tech.distribuidora.features.clients.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findByOrderCode(UUID orderCode);

    //List<OrderEntity> getByOrderCode(UUID orderCode);

    List<OrderEntity> findByClientId(ClientEntity clientId);

    List<OrderEntity> findByOrderStatus(Status orderStatus);
    List<OrderEntity> findByClientIdAndOrderStatus(ClientEntity clientId, Status status);

    @Query("SELECT o FROM OrderEntity o WHERE o.orderDate >= :fechaInicio AND o.orderDate <= :fechaFin AND o.orderStatus = 'COMPLETED'")
    List<OrderEntity> findByFechaInicioBetween(
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin
    );
}
