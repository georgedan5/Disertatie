package com.fitnessclub.REPOSITORIES;

import com.fitnessclub.DOMAIN.Abonament;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbonamentRepository extends PagingAndSortingRepository<Abonament,Long> {
}
