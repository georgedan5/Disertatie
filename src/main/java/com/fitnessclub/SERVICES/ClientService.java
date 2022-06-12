package com.fitnessclub.SERVICES;

import com.fitnessclub.DOMAIN.Client;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClientService {
    Page<Client> findAll(int pageNumber, String sortField, String sortDirection);
    List<Client> findAll();
    Client findById(long id);
    Client save(Client client);
    void deleteById(long id);
    Client findByEmail(String email);
}
