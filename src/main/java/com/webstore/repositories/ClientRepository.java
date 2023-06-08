package com.webstore.repositories;

import com.webstore.entities.ClientEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Long> {
    Long countClientByRegistration(long registration);

    ClientEntity findClientEntityByClientEmail(String ClientEmail);

    boolean existsByClientName(String clientName);
    boolean existsByClientEmail(String clientEmail);
    boolean existsByClientCpf(String clientCpf);
}
