package com.fitnessclub.REPOSITORIES;


import com.fitnessclub.DOMAIN.Comanda;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComandaRepository extends PagingAndSortingRepository<Comanda,Long> {
}
