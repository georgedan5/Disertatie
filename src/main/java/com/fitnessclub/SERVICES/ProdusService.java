package com.fitnessclub.SERVICES;


import com.fitnessclub.DOMAIN.Produs;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProdusService {

    Page<Produs> findAll(int pageNumber, String sortField, String sortDirection);
    List<Produs> findAll();
    Produs findById(long id);
    Produs save(Produs produs);
    void deleteById(long id);
}
