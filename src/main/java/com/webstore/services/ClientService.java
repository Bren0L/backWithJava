package com.webstore.services;

import com.webstore.entities.ClientEntity;
import com.webstore.repositories.ClientRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public boolean verifyClient(ClientEntity client){
        ClientEntity clientEntity = repository.findClientEntityByClientEmail(client.getClientEmail());
        return clientEntity != null && clientEntity.getClientPassword().equals(client.getClientPassword());
    }

    public ClientEntity getClientByEmail(String email){
        return repository.findClientEntityByClientEmail(email);
    }

    public List<ClientEntity> getAllClients(){
        return (List<ClientEntity>) repository.findAll();
    }

    @Transactional
    public ClientEntity save(ClientEntity client) {
        return repository.save(client);
    }

    public boolean existsByClientName(String clientName){
        return repository.existsByClientName(clientName);
    }

    public boolean existsByClientEmail(String clientEmail){
        return repository.existsByClientEmail(clientEmail);
    }

    public boolean existsByClientCpf(String clientCpf){
        return repository.existsByClientCpf(clientCpf);
    }

    public Optional<ClientEntity> getClient(long id) {
       return repository.findById(id);
    }

    @Transactional
    public void deleteClient(ClientEntity client) {
       repository.delete(client);
    }
}
