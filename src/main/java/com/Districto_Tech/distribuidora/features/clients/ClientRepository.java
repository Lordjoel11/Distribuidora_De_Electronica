package com.Districto_Tech.distribuidora.features.clients;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {

    Optional<ClientEntity> findByDNI(String DNI);
    List<ClientEntity> getByDNI(String DNI);

}
