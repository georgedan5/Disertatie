package com.fitnessclub.SERVICES;

import com.fitnessclub.DOMAIN.Mesaj;
import com.fitnessclub.DOMAIN.Utilizator;
import com.fitnessclub.EXCEPTIONS.ResourceNotFoundException;
import com.fitnessclub.REPOSITORIES.MesajRepository;
import com.fitnessclub.REPOSITORIES.UtilizatorRepository;
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
public class UtilizatorServiceImpl implements UtilizatorService {
    @Autowired
    UtilizatorRepository utilizatorRepository;

    public UtilizatorServiceImpl(UtilizatorRepository utilizatorRepository) {
        this.utilizatorRepository = utilizatorRepository;
    }
    @Override
    public Page<Utilizator> findAll(int pageNumber, String sortField, String sortDirection) {

        Sort sort=Sort.by(sortField);
        sort=sortDirection.equals("asc") ? sort.ascending(): sort.descending();
        Pageable pageable= PageRequest.of(pageNumber-1,3,sort);

        List<Utilizator> utilizators=new LinkedList<>();
        utilizatorRepository.findAll().iterator().forEachRemaining(utilizators::add);
        log.info("Metoda findAll din UtilizatorRepository a fost apelata! ");
        utilizators.forEach(utilizator -> log.info(utilizator.getUsername()));

        return utilizatorRepository.findAll(pageable);
    }
    @Override
    public List<Utilizator> findAll() {
        List<Utilizator> utilizators=new LinkedList<>();
        utilizatorRepository.findAll().iterator().forEachRemaining(utilizators::add);
        return utilizators;
    }
    @Override
    public Utilizator findById(long id) {
        Optional<Utilizator> ok=utilizatorRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Utilizatorul nu exista!");
        }
        log.info("Metoda findById din UtilizatorRepository a fost apelata pentru  id-ul:"+id);

        return ok.get();

    }

    @Override
    public Utilizator save(Utilizator utilizator) {
        Utilizator savedutilizator=utilizatorRepository.save(utilizator);
        log.info("Metoda save din UtilizatorRepository a fost apelata pentru mesajul:"+utilizator.getId());

        return  savedutilizator;
    }

    @Override
    public void deleteById(long id) {
        Optional<Utilizator> ok=utilizatorRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Utilizatorul nu exista!");
        }
        log.info("Metoda deleteById din UtilizatorRepository a fost apelata pentru  id-ul:"+id);

        utilizatorRepository.deleteById(id);
    }

}
