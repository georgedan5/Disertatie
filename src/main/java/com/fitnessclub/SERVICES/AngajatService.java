package com.fitnessclub.SERVICES;

import com.fitnessclub.DOMAIN.Angajat;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AngajatService {
    Page<Angajat> findAll(int pageNumber, String sortField, String sortDirection);
    Page<Angajat> findAll2(int pageNumber, String sortField, String sortDirection);
    List<Angajat> findAll();
    Angajat findById(long id);
    Angajat save(Angajat angajat);
    void deleteById(long id);
}
