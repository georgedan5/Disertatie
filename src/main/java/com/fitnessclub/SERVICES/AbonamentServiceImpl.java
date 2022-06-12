package com.fitnessclub.SERVICES;

import com.fitnessclub.DOMAIN.Abonament;
import com.fitnessclub.DOMAIN.Abonament_efectuat;
import com.fitnessclub.EXCEPTIONS.ResourceNotFoundException;
import com.fitnessclub.REPOSITORIES.AbonamentEfectuatRepository;
import com.fitnessclub.REPOSITORIES.AbonamentRepository;
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
public class AbonamentServiceImpl implements AbonamentService {
    @Autowired
    AbonamentRepository abonamentRepository;


    public AbonamentServiceImpl(AbonamentRepository abonamentRepository) {
        this.abonamentRepository = abonamentRepository;
    }



    @Override
    public Abonament save(Abonament abonament) {
        Abonament savedcaboanemnt=abonamentRepository.save(abonament);
        log.info("Metoda save din AboanamentRepository a fost apelata pentru abonamentul:"+abonament.getId());

        return  savedcaboanemnt;
    }

    @Override
    public Page<Abonament> findAll(int pageNumber, String sortField, String sortDirection) {

        Sort sort=Sort.by(sortField);
        sort=sortDirection.equals("asc") ? sort.ascending(): sort.descending();
        Pageable pageable= PageRequest.of(pageNumber-1,3,sort);

        List<Abonament> abs=new LinkedList<>();
        abonamentRepository.findAll().iterator().forEachRemaining(abs::add);
        log.info("Metoda findAll din AbonamentRepository a fost apelata! ");
        abs.forEach(abonament -> log.info(abonament.getDenumire()));

        return abonamentRepository.findAll(pageable);
    }
    @Override
    public List<Abonament> findAll() {
        List<Abonament> ab=new LinkedList<>();
        abonamentRepository.findAll().iterator().forEachRemaining(ab::add);
        return ab;
    }
    @Override
    public Abonament findById(long id) {
        Optional<Abonament> ok=abonamentRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Abonamentul nu exista!");
        }
        log.info("Metoda findById din AbonamentRepository a fost apelata pentru  id-ul:"+id);

        return ok.get();

    }

    @Override
    public void deleteById(long id) {
        Optional<Abonament> ok=abonamentRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Abonamentul nu exista!");
        }
        log.info("Metoda deleteById din AbonamentRepository a fost apelata pentru  id-ul:"+id);

        abonamentRepository.deleteById(id);
    }



}
