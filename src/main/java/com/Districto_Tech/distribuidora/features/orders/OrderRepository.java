package com.Districto_Tech.distribuidora.features.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findByOrderCode(UUID orderCode);

    List<OrderEntity> getByOrderCode(UUID orderCode);

    @Query("SELECT o FROM OrderEntity o WHERE o.orderDate >= :fechaInicio AND o.orderDate <= :fechaFin AND o.orderStatus = 'COMPLETED'")
    List<OrderEntity> findByFechaInicioBetween(
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin
    );
}
