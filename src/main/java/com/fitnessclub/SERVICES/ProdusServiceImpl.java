package com.fitnessclub.SERVICES;


import com.fitnessclub.DOMAIN.Produs;
import com.fitnessclub.EXCEPTIONS.ResourceNotFoundException;
import com.fitnessclub.REPOSITORIES.ProdusRepository;
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
public class ProdusServiceImpl implements ProdusService {

    @Autowired
    ProdusRepository produsRepository;


    public ProdusServiceImpl(ProdusRepository produsRepository) {
        this.produsRepository = produsRepository;
    }
    @Override
    public Page<Produs> findAll(int pageNumber, String sortField, String sortDirection) {

        Sort sort=Sort.by(sortField);
        sort=sortDirection.equals("asc") ? sort.ascending(): sort.descending();
        Pageable pageable= PageRequest.of(pageNumber-1,9999,sort);

        List<Produs> produses=new LinkedList<>();
        produsRepository.findAll().iterator().forEachRemaining(produses::add);
        log.info("Metoda findAll din ProdusRepository a fost apelata! ");
        produses.forEach(produs -> log.info(produs.getDenumire()));

        return produsRepository.findAll(pageable);
    }
    @Override
    public List<Produs> findAll() {
        List<Produs> produss=new LinkedList<>();
        produsRepository.findAll().iterator().forEachRemaining(produss::add);
        return produss;
    }
    @Override
    public Produs findById(long id) {
        Optional<Produs> ok=produsRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Produsul nu exista!");
        }
        log.info("Metoda findById din ProdusRepository a fost apelata pentru  id-ul:"+id);

        return ok.get();

    }

    @Override
    public Produs save(Produs produs) {
        Produs savedprodus=produsRepository.save(produs);
        log.info("Metoda save din ProdusRepository a fost apelata pentru produsul:"+produs.getDenumire());

        return  savedprodus;
    }

    @Override
    public void deleteById(long id) {
        Optional<Produs> ok=produsRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Produsul nu exista!");
        }
        log.info("Metoda deleteById din ProdusRepository a fost apelata pentru  id-ul:"+id);

        produsRepository.deleteById(id);
    }

}
