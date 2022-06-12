package com.fitnessclub.SERVICES;

import com.fitnessclub.DOMAIN.Rol;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RolService {
    Page<Rol> findAll(int pageNumber, String sortField, String sortDirection);
    List<Rol> findAll();
    Rol findById(long id);
    Rol save(Rol rol);
    void deleteById(long id);
}
