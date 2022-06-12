package com.fitnessclub.SERVICES;


import com.fitnessclub.DOMAIN.Cos;
import com.fitnessclub.DOMAIN.Istoric_client;
import com.fitnessclub.EXCEPTIONS.ResourceNotFoundException;
import com.fitnessclub.REPOSITORIES.CosRepository;
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
public class CosServiceImpl implements CosService{

    @Autowired
    CosRepository cosRepository;


    public CosServiceImpl(CosRepository cosRepository) {
        this.cosRepository = cosRepository;
    }
    @Override
    public Page<Cos> findAll(int pageNumber, String sortField, String sortDirection) {

        Sort sort=Sort.by(sortField);
        sort=sortDirection.equals("asc") ? sort.ascending(): sort.descending();
        Pageable pageable= PageRequest.of(pageNumber-1,1000,sort);

        List<Cos> cosList=new LinkedList<>();
        cosRepository.findAll().iterator().forEachRemaining(cosList::add);
        log.info("Metoda findAll din CosRepository a fost apelata! ");
        cosList.forEach(cos -> log.info(cos.getDenumire()));

        return cosRepository.findAll(pageable);
    }
    @Override
    public List<Cos> findAll() {
        List<Cos> cosList=new LinkedList<>();
        cosRepository.findAll().iterator().forEachRemaining(cosList::add);
        return cosList;
    }
    @Override
    public Cos findById(long id) {
        Optional<Cos> ok=cosRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Cosul nu exista!");
        }
        log.info("Metoda findById din CosRepository a fost apelata pentru  id-ul:"+id);

        return ok.get();

    }

    @Override
    public Cos save(Cos cos) {
        Cos savedcos=cosRepository.save(cos);
        log.info("Metoda save din CosRepository a fost apelata pentru produsul:"+cos.getDenumire());

        return  savedcos;
    }

    @Override
    public void deleteById(long id) {
        Optional<Cos> ok=cosRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Produsul nu exista!");
        }
        log.info("Metoda deleteById din CosRepository a fost apelata pentru  id-ul:"+id);

        cosRepository.deleteById(id);
    }

    @Override
    public List<Cos> findByIdu(long id) {
        List<Cos> cosList=new LinkedList<>();
        cosRepository.findByIdu(id).iterator().forEachRemaining(cosList::add);
        return cosList;
    }



}
