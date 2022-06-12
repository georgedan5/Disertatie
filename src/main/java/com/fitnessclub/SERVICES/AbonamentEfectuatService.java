package com.fitnessclub.SERVICES;

import com.fitnessclub.DOMAIN.Abonament_efectuat;
import com.fitnessclub.DOMAIN.Cos;
import com.fitnessclub.DOMAIN.Mesaj;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;


public interface AbonamentEfectuatService {
    Abonament_efectuat save(Abonament_efectuat abonament_efectuat);
    Page<Abonament_efectuat> findAll(int pageNumber, String sortField, String sortDirection);
    List<Abonament_efectuat> findAll();
    Abonament_efectuat findById(long id);
    void deleteById(long id);

    List<Abonament_efectuat> findByclient_id(long id);

}
