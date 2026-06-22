package com.Districto_Tech.distribuidora.features.employees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    Boolean existsByCUIL(String CUIL);
    Optional<EmployeeEntity> findByUser_Id(Long userId);
}
