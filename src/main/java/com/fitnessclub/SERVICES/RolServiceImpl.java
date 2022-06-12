package com.fitnessclub.SERVICES;

import com.fitnessclub.DOMAIN.Mesaj;
import com.fitnessclub.DOMAIN.Rol;
import com.fitnessclub.EXCEPTIONS.ResourceNotFoundException;
import com.fitnessclub.REPOSITORIES.MesajRepository;
import com.fitnessclub.REPOSITORIES.RolRepository;
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
public class RolServiceImpl implements RolService {

    @Autowired
    RolRepository rolRepository;


    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }
    @Override
    public Page<Rol> findAll(int pageNumber, String sortField, String sortDirection) {

        Sort sort=Sort.by(sortField);
        sort=sortDirection.equals("asc") ? sort.ascending(): sort.descending();
        Pageable pageable= PageRequest.of(pageNumber-1,3,sort);

        List<Rol> rolList=new LinkedList<>();
        rolRepository.findAll().iterator().forEachRemaining(rolList::add);
        log.info("Metoda findAll din RolRepository a fost apelata! ");
        rolList.forEach(rol -> log.info(rol.getRol()));

        return rolRepository.findAll(pageable);
    }
    @Override
    public List<Rol> findAll() {
        List<Rol> rols=new LinkedList<>();
        rolRepository.findAll().iterator().forEachRemaining(rols::add);
        return rols;
    }
    @Override
    public Rol findById(long id) {
        Optional<Rol> ok=rolRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Rolul nu exista!");
        }
        log.info("Metoda findById din RolRepository a fost apelata pentru  id-ul:"+id);

        return ok.get();

    }

    @Override
    public Rol save(Rol rol) {
        Rol savedrol=rolRepository.save(rol);
        log.info("Metoda save din RolRepository a fost apelata pentru rolul:"+rol.getId());

        return  savedrol;
    }

    @Override
    public void deleteById(long id) {
        Optional<Rol> ok=rolRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Rolul nu exista!");
        }
        log.info("Metoda deleteById din RolRepository a fost apelata pentru  id-ul:"+id);

        rolRepository.deleteById(id);
    }

}
