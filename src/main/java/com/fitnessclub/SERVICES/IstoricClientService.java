package com.fitnessclub.SERVICES;

import com.fitnessclub.DOMAIN.Angajat;
import com.fitnessclub.DOMAIN.Client;
import com.fitnessclub.DOMAIN.Istoric_client;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IstoricClientService {
    Page<Istoric_client> findAll(int pageNumber, String sortField, String sortDirection);
    List<Istoric_client> findAll();
    List<Istoric_client> findById(long id);
    Istoric_client save(Istoric_client istoric_client);
    void deleteById(long id);
   // Page<Istoric_client> findByGreutatea (int pageNumber, String sortField, String sortDirection,long greutatea);
    ///aici
    List<Istoric_client> findByGreutatea(float greutatea);
    List<Istoric_client> findByIdc(long idc);


}
