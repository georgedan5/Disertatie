package com.fitnessclub.SERVICES;


import com.fitnessclub.DOMAIN.Detalii_comanda;
import com.fitnessclub.EXCEPTIONS.ResourceNotFoundException;
import com.fitnessclub.REPOSITORIES.CosRepository;
import com.fitnessclub.REPOSITORIES.DetaliiComandaRepository;
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
public class DetaliiComandaServiceImpl implements DetaliiComandaService {

    @Autowired
    DetaliiComandaRepository detaliiComandaRepository;


    public DetaliiComandaServiceImpl(DetaliiComandaRepository detaliiComandaRepository) {
        this.detaliiComandaRepository = detaliiComandaRepository;
    }
    @Override
    public Page<Detalii_comanda> findAll(int pageNumber, String sortField, String sortDirection) {

        Sort sort=Sort.by(sortField);
        sort=sortDirection.equals("asc") ? sort.ascending(): sort.descending();
        Pageable pageable= PageRequest.of(pageNumber-1,1000,sort);

        List<Detalii_comanda> ccc=new LinkedList<>();
        detaliiComandaRepository.findAll().iterator().forEachRemaining(ccc::add);
        log.info("Metoda findAll din CosRepository a fost apelata! ");


        return detaliiComandaRepository.findAll(pageable);
    }
    @Override
    public List<Detalii_comanda> findAll() {
        List<Detalii_comanda> ccc=new LinkedList<>();
        detaliiComandaRepository.findAll().iterator().forEachRemaining(ccc::add);
        return ccc;
    }
    @Override
    public Detalii_comanda findById(long id) {
        Optional<Detalii_comanda> ok=detaliiComandaRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Cosul nu exista!");
        }
        log.info("Metoda findById din CosRepository a fost apelata pentru  id-ul:"+id);

        return ok.get();

    }

    @Override
    public Detalii_comanda save(Detalii_comanda detalii_comanda) {
        Detalii_comanda saveddc=detaliiComandaRepository.save(detalii_comanda);
        log.info("Metoda save din CosRepository a fost apelata pentru produsul:"+detalii_comanda.getId());

        return  saveddc;
    }

    @Override
    public void deleteById(long id) {
        Optional<Detalii_comanda> ok=detaliiComandaRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Produsul nu exista!");
        }
        log.info("Metoda deleteById din CosRepository a fost apelata pentru  id-ul:"+id);

        detaliiComandaRepository.deleteById(id);
    }

    @Override
    public List<Detalii_comanda> findByComanda_Id(long id) {
        List<Detalii_comanda> ccd=new LinkedList<>();
        detaliiComandaRepository.findByComanda_Id(id).iterator().forEachRemaining(ccd::add);
        return ccd;
    }
}
