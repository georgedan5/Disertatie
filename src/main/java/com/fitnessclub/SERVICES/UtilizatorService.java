package com.fitnessclub.SERVICES;

import com.fitnessclub.DOMAIN.Utilizator;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UtilizatorService {
    Page<Utilizator> findAll(int pageNumber, String sortField, String sortDirection);
    List<Utilizator> findAll();
    Utilizator findById(long id);
    Utilizator save(Utilizator utilizator);
    void deleteById(long id);
}
