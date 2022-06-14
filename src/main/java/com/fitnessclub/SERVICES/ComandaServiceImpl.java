package com.fitnessclub.SERVICES;


import com.fitnessclub.DOMAIN.Comanda;
import com.fitnessclub.EXCEPTIONS.ResourceNotFoundException;
import com.fitnessclub.REPOSITORIES.AngajatRepository;
import com.fitnessclub.REPOSITORIES.ComandaRepository;
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
public class ComandaServiceImpl implements ComandaService {

    @Autowired
    ComandaRepository comandaRepository;

    public ComandaServiceImpl(ComandaRepository comandaRepository) {
        this.comandaRepository = comandaRepository;
    }
    @Override
    public Page<Comanda> findAll(int pageNumber, String sortField, String sortDirection) {

        Sort sort=Sort.by(sortField);
        sort=sortDirection.equals("asc") ? sort.ascending(): sort.descending();
        Pageable pageable= PageRequest.of(pageNumber-1,100,sort);

        List<Comanda> comandaList=new LinkedList<>();
        comandaRepository.findAll().iterator().forEachRemaining(comandaList::add);
        log.info("Metoda findAll din ComandaRepository a fost apelata! ");
        comandaList.forEach(comanda -> log.info(comanda.getNume()));

        return comandaRepository.findAll(pageable);
    }


    @Override
    public List<Comanda> findAll() {
        List<Comanda> comandas=new LinkedList<>();
        comandaRepository.findAll().iterator().forEachRemaining(comandas::add);
        return comandas;
    }


    @Override
    public Comanda findById(long id) {
        Optional<Comanda> ok=comandaRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Comanda nu exista!");
        }
        log.info("Metoda findById dinComandaRepository a fost apelata pentru comanda cu id-ul:"+id);

        return ok.get();

    }

    @Override
    public Comanda save(Comanda comanda) {
        Comanda savedcomanda=comandaRepository.save(comanda);
        log.info("Metoda save din ComandaRepository a fost apelata pentru comanda:"+comanda.getNume());

        return  savedcomanda;
    }

    @Override
    public void deleteById(long id) {
        Optional<Comanda> ok=comandaRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Angajatul nu exista!");
        }
        log.info("Metoda deleteById din ComandaRepository a fost apelata pentru comanda cu id-ul:"+id);

        comandaRepository.deleteById(id);
    }


}
