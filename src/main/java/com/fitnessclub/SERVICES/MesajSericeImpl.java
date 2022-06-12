package com.fitnessclub.SERVICES;

import com.fitnessclub.DOMAIN.Mesaj;
import com.fitnessclub.EXCEPTIONS.ResourceNotFoundException;
import com.fitnessclub.REPOSITORIES.MesajRepository;
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
public class MesajSericeImpl implements MesajService {
    @Autowired
    MesajRepository mesajRepository;


    public MesajSericeImpl(MesajRepository mesajRepository) {
        this.mesajRepository = mesajRepository;
    }
    @Override
    public Page<Mesaj> findAll(int pageNumber, String sortField, String sortDirection) {

        Sort sort=Sort.by(sortField);
        sort=sortDirection.equals("asc") ? sort.ascending(): sort.descending();
        Pageable pageable= PageRequest.of(pageNumber-1,3,sort);

        List<Mesaj> mesajList=new LinkedList<>();
        mesajRepository.findAll().iterator().forEachRemaining(mesajList::add);
        log.info("Metoda findAll din MesajRepository a fost apelata! ");
        mesajList.forEach(mesaj -> log.info(mesaj.getNume()));

        return mesajRepository.findAll(pageable);
    }
    @Override
    public List<Mesaj> findAll() {
        List<Mesaj> mesajs=new LinkedList<>();
        mesajRepository.findAll().iterator().forEachRemaining(mesajs::add);
        return mesajs;
    }
    @Override
    public Mesaj findById(long id) {
        Optional<Mesaj> ok=mesajRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Mesajul nu exista!");
        }
        log.info("Metoda findById din MesajRepository a fost apelata pentru  id-ul:"+id);

        return ok.get();

    }

    @Override
    public Mesaj save(Mesaj mesaj) {
        Mesaj savedmesaj=mesajRepository.save(mesaj);
        log.info("Metoda save din MesajRepository a fost apelata pentru mesajul:"+mesaj.getId());

        return  savedmesaj;
    }

    @Override
    public void deleteById(long id) {
        Optional<Mesaj> ok=mesajRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Mesajul nu exista!");
        }
        log.info("Metoda deleteById din MesajRepository a fost apelata pentru  id-ul:"+id);

        mesajRepository.deleteById(id);
    }

}
