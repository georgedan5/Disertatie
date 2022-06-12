package com.fitnessclub.SERVICES;

import com.fitnessclub.DOMAIN.Angajat;
import com.fitnessclub.EXCEPTIONS.ResourceNotFoundException;
import com.fitnessclub.REPOSITORIES.AngajatRepository;
import com.fitnessclub.REPOSITORIES.ClientRepository;
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
public class AngajatServiceImpl implements AngajatService {
    @Autowired
    AngajatRepository angajatRepository;


    public AngajatServiceImpl(AngajatRepository angajatRepository) {
        this.angajatRepository = angajatRepository;
    }
    @Override
    public Page<Angajat> findAll(int pageNumber, String sortField, String sortDirection) {

        Sort sort=Sort.by(sortField);
        sort=sortDirection.equals("asc") ? sort.ascending(): sort.descending();
        Pageable pageable= PageRequest.of(pageNumber-1,100,sort);

        List<Angajat> angajatList=new LinkedList<>();
        angajatRepository.findAll().iterator().forEachRemaining(angajatList::add);
        log.info("Metoda findAll din AngajatRepository a fost apelata! ");
        angajatList.forEach(angajat -> log.info(angajat.getNume()));

        return angajatRepository.findAll(pageable);
    }

    @Override
    public Page<Angajat> findAll2(int pageNumber, String sortField, String sortDirection) {

        Sort sort=Sort.by(sortField);
        sort=sortDirection.equals("asc") ? sort.ascending(): sort.descending();
        Pageable pageable= PageRequest.of(pageNumber-1,5,sort);

        List<Angajat> angajatList=new LinkedList<>();
        angajatRepository.findAll().iterator().forEachRemaining(angajatList::add);
        log.info("Metoda findAll din AngajatRepository a fost apelata! ");
        angajatList.forEach(angajat -> log.info(angajat.getNume()));

        return angajatRepository.findAll(pageable);
    }

    @Override
    public List<Angajat> findAll() {
        List<Angajat> angajats=new LinkedList<>();
        angajatRepository.findAll().iterator().forEachRemaining(angajats::add);
        return angajats;
    }
    @Override
    public Angajat findById(long id) {
        Optional<Angajat> ok=angajatRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Angajatul nu exista!");
        }
        log.info("Metoda findById din AngajatRepository a fost apelata pentru angajatul cu id-ul:"+id);

        return ok.get();

    }

    @Override
    public Angajat save(Angajat angajat) {
        Angajat savedcangajat=angajatRepository.save(angajat);
        log.info("Metoda save din AngajatRepository a fost apelata pentru angajatul:"+angajat.getNume());

        return  savedcangajat;
    }

    @Override
    public void deleteById(long id) {
        Optional<Angajat> ok=angajatRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Angajatul nu exista!");
        }
        log.info("Metoda deleteById din AngajatRepository a fost apelata pentru angajatul cu id-ul:"+id);

        angajatRepository.deleteById(id);
    }


}
