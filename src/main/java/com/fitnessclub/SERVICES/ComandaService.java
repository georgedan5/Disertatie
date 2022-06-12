package com.fitnessclub.SERVICES;

import com.fitnessclub.DOMAIN.Comanda;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ComandaService {
    Page<Comanda> findAll(int pageNumber, String sortField, String sortDirection);
    List<Comanda> findAll();
    Comanda findById(long id);
    Comanda save(Comanda comanda);
    void deleteById(long id);
}
