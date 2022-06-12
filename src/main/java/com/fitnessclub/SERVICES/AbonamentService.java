package com.fitnessclub.SERVICES;

import com.fitnessclub.DOMAIN.Abonament;
import com.fitnessclub.DOMAIN.Angajat;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AbonamentService {
    Page<Abonament> findAll(int pageNumber, String sortField, String sortDirection);
    List<Abonament> findAll();
    Abonament findById(long id);
    Abonament save(Abonament abonament);
    void deleteById(long id);
}
