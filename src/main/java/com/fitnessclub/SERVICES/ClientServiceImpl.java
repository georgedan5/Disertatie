package com.fitnessclub.SERVICES;

import com.fitnessclub.DOMAIN.Client;
import com.fitnessclub.EXCEPTIONS.ResourceNotFoundException;
import com.fitnessclub.REPOSITORIES.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;


    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    @Override
    public Page<Client> findAll(int pageNumber, String sortField, String sortDirection) {

        Sort sort=Sort.by(sortField);
        sort=sortDirection.equals("asc") ? sort.ascending(): sort.descending();
        Pageable pageable= PageRequest.of(pageNumber-1,3,sort);

        List<Client> clientList=new LinkedList<>();
        clientRepository.findAll().iterator().forEachRemaining(clientList::add);
        log.info("Metoda findAll din ClientRepository a fost apelata! ");
        clientList.forEach(client -> log.info(client.getNume()));

        return clientRepository.findAll(pageable);
    }
    @Override
    public List<Client> findAll() {
        List<Client> clients=new LinkedList<>();
        clientRepository.findAll().iterator().forEachRemaining(clients::add);
        return clients;
    }
    @Override
    public Client findById(long id) {
        Optional<Client> ok=clientRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Clientul nu exista!");
        }
        log.info("Metoda findById din ClientRepository a fost apelata pentru clientul cu id-ul:"+id);

        return ok.get();

    }

    @Override
    public Client save(Client product) {
        Client savedclient=clientRepository.save(product);
        log.info("Metoda save din ClientRepository a fost apelata pentru clientul:"+product.getNume());

        return  savedclient;
    }

    @Override
    public void deleteById(long id) {
        Optional<Client> ok=clientRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Clientul nu exista!");
        }
        log.info("Metoda deleteById din ClientRepository a fost apelata pentru clientul cu id-ul:"+id);

        clientRepository.deleteById(id);
    }

    @Override
    public Client findByEmail(String email) {
        Client ok=clientRepository.findByEmail(email);
        return ok;

    }
}
