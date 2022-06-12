package com.fitnessclub.SERVICES;

import com.fitnessclub.DOMAIN.Cos;
import com.fitnessclub.DOMAIN.Istoric_client;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CosService {
    Page<Cos> findAll(int pageNumber, String sortField, String sortDirection);
    List<Cos> findAll();
    Cos findById(long id);
    Cos save(Cos cos);
    void deleteById(long id);

    //List<Cos> findByPret(double pret);
    List<Cos> findByIdu(long id);
}
