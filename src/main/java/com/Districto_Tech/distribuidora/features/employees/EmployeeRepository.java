package com.Districto_Tech.distribuidora.features.employees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    //Optional<EmployeeEntity> findByCUIL(Long CUIL);
   // Optional<EmployeeEntity> findByNameAndSurname(String name, String surname);
   // List<EmployeeEntity> findByRole(Role role);
    Boolean existsByCUIL(String CUIL);

}
