package com.fitnessclub.SERVICES;

import com.fitnessclub.DOMAIN.Abonament_efectuat;
import com.fitnessclub.DOMAIN.Angajat;
import com.fitnessclub.DOMAIN.Cos;
import com.fitnessclub.DOMAIN.Mesaj;
import com.fitnessclub.EXCEPTIONS.ResourceNotFoundException;
import com.fitnessclub.REPOSITORIES.AbonamentEfectuatRepository;
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
public class AbonamentEfectuatServiceImpl implements AbonamentEfectuatService {

    @Autowired
    AbonamentEfectuatRepository abonamentEfectuatRepository;


    public AbonamentEfectuatServiceImpl(AbonamentEfectuatRepository abonamentEfectuatRepository) {
        this.abonamentEfectuatRepository = abonamentEfectuatRepository;
    }



    @Override
    public Abonament_efectuat save(Abonament_efectuat abonament_efectuat) {
        Abonament_efectuat savedcaboanemnt=abonamentEfectuatRepository.save(abonament_efectuat);
        log.info("Metoda save din AboanamentRepository a fost apelata pentru abonamentul:"+abonament_efectuat.getId());

        return  savedcaboanemnt;
    }

    @Override
    public Page<Abonament_efectuat> findAll(int pageNumber, String sortField, String sortDirection) {

        Sort sort=Sort.by(sortField);
        sort=sortDirection.equals("asc") ? sort.ascending(): sort.descending();
        Pageable pageable= PageRequest.of(pageNumber-1,3,sort);

        List<Abonament_efectuat> abonament_efectuats=new LinkedList<>();
        abonamentEfectuatRepository.findAll().iterator().forEachRemaining(abonament_efectuats::add);
        log.info("Metoda findAll din Abonament_efectuatRepository a fost apelata! ");
        abonament_efectuats.forEach(abonament_efectuat -> log.info(abonament_efectuat.getAbonament().getDenumire()));

        return abonamentEfectuatRepository.findAll(pageable);
    }
    @Override
    public List<Abonament_efectuat> findAll() {
        List<Abonament_efectuat> ab=new LinkedList<>();
        abonamentEfectuatRepository.findAll().iterator().forEachRemaining(ab::add);
        return ab;
    }
    @Override
    public Abonament_efectuat findById(long id) {
        Optional<Abonament_efectuat> ok=abonamentEfectuatRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Abonamentul nu exista!");
        }
        log.info("Metoda findById din AbonamentRepository a fost apelata pentru  id-ul:"+id);

        return ok.get();

    }

    @Override
    public void deleteById(long id) {
        Optional<Abonament_efectuat> ok=abonamentEfectuatRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Abonamentul nu exista!");
        }
        log.info("Metoda deleteById din AbonamentRepository a fost apelata pentru  id-ul:"+id);

        abonamentEfectuatRepository.deleteById(id);
    }

    @Override
    public List<Abonament_efectuat> findByclient_id(long id) {
        List<Abonament_efectuat> abh=new LinkedList<>();
        abonamentEfectuatRepository.findByclient_id(id).iterator().forEachRemaining(abh::add);
        return abh;
    }

}
