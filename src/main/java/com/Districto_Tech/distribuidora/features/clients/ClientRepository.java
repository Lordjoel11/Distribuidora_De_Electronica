package com.Districto_Tech.distribuidora.features.clients;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    Optional<ClientEntity> findByNameAndSurname(String NameAndSurname);
    List<ClientEntity> getByDNI(String DNI);


}
