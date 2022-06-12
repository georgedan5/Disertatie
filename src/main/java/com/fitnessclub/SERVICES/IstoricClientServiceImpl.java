package com.fitnessclub.SERVICES;


import com.fitnessclub.DOMAIN.Client;
import com.fitnessclub.DOMAIN.Istoric_client;
import com.fitnessclub.EXCEPTIONS.ResourceNotFoundException;
import com.fitnessclub.REPOSITORIES.IstoricClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class IstoricClientServiceImpl implements IstoricClientService {
    @Autowired
    IstoricClientRepository istoricClientRepository;


    public IstoricClientServiceImpl(IstoricClientRepository istoricClientRepository) {
        this.istoricClientRepository = istoricClientRepository;
    }
    @Override
    public Page<Istoric_client> findAll(int pageNumber, String sortField, String sortDirection) {

        Sort sort=Sort.by(sortField);
        sort=sortDirection.equals("asc") ? sort.ascending(): sort.descending();
        Pageable pageable= PageRequest.of(pageNumber-1,15,sort);

        List<Istoric_client> istoric_clients=new LinkedList<>();
        istoricClientRepository.findAll().iterator().forEachRemaining(istoric_clients::add);
        log.info("Metoda findAll din IstoricClientRepository a fost apelata! ");
        istoric_clients.forEach(istoric_client -> log.info(istoric_client.getGreutatea().toString()));
       // istoric_clients.forEach(ist -> log.info(ist.getId()));

        return istoricClientRepository.findAll(pageable);
    }

    //asd
    @Override
    public List<Istoric_client> findByGreutatea(float greutatea) {
        List<Istoric_client> istoric_clients=new LinkedList<>();
        istoricClientRepository.findByGreutatea(greutatea).iterator().forEachRemaining(istoric_clients::add);
        return istoric_clients;
    }
    ///qqqq
    @Override
    public List<Istoric_client> findByIdc(long idc) {
        List<Istoric_client> istoric_clients=new LinkedList<>();
        istoricClientRepository.findByIdc(idc).iterator().forEachRemaining(istoric_clients::add);
        return istoric_clients;
    }
    @Override
    public List<Istoric_client> findAll() {
        List<Istoric_client> istoric_clients=new LinkedList<>();
        istoricClientRepository.findAll().iterator().forEachRemaining(istoric_clients::add);
        return istoric_clients;
    }
    @Override
    public List<Istoric_client> findById(long id) {
        List<Istoric_client> ok=istoricClientRepository.findById(id);
        if(ok.isEmpty()){
            throw new ResourceNotFoundException("Istoricul pentru acest client nu exista!");
        }
        log.info("Metoda findById din IstoricClientRepository a fost apelata pentru clientul cu id-ul:"+id);

        return ok;

    }

    @Override
    public Istoric_client save(Istoric_client istoric_client) {
        Istoric_client savedclient_ist=istoricClientRepository.save(istoric_client);
        log.info("Metoda save din ClientRepository a fost apelata pentru clientul:"+istoric_client.getId());

        return  savedclient_ist;
    }

    @Override
    public void deleteById(long id) {
        /*
        Optional<Istoric_client> ok=istoricClientRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Clientul nu exista!");
        }
        log.info("Metoda deleteById din ClientRepository a fost apelata pentru clientul cu id-ul:"+id);

        istoricClientRepository.deleteById(id);

         */
    }

    ///aici
    /*
    @Override
    public List<Istoric_client> findByGreutatea(long greutatea) {
        List<Istoric_client>  istoricClients=new LinkedList<>();
        istoricClientRepository.findByGreutatea(greutatea).iterator().forEachRemaining(istoricClients::add);

        return  istoricClients;
    }


     */

}
