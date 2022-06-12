package com.fitnessclub.REPOSITORIES;

import com.fitnessclub.DOMAIN.Utilizator;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilizatorRepository extends PagingAndSortingRepository<Utilizator,Long> {
}
