package com.fitnessclub.SERVICES;

import com.fitnessclub.DOMAIN.Detalii_comanda;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DetaliiComandaService {

    Page<Detalii_comanda> findAll(int pageNumber, String sortField, String sortDirection);
    List<Detalii_comanda> findAll();
    Detalii_comanda findById(long id);
    Detalii_comanda save(Detalii_comanda detalii_comanda);
    void deleteById(long id);

    //List<Cos> findByPret(double pret);
    List<Detalii_comanda> findByComanda_Id(long id);
}
