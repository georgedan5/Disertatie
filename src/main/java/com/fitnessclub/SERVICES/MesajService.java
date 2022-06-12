package com.fitnessclub.SERVICES;

import com.fitnessclub.DOMAIN.Mesaj;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MesajService {
    Page<Mesaj> findAll(int pageNumber, String sortField, String sortDirection);
    List<Mesaj> findAll();
    Mesaj findById(long id);
    Mesaj save(Mesaj mesaj);
    void deleteById(long id);
}
