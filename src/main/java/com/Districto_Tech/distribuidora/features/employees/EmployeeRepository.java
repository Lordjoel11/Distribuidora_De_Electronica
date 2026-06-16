package com.Districto_Tech.distribuidora.features.employees;

import com.Districto_Tech.distribuidora.features.employees.misc.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    Optional<EmployeeEntity> findByCUIL(Long CUIL);
    Optional<EmployeeEntity> findByNameAndSurname(String name, String surname);
    List<EmployeeEntity> findByRole(Role role);
    Boolean existsByCUIL(String CUIL);

}
