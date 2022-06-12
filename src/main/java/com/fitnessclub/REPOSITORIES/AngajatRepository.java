package com.fitnessclub.REPOSITORIES;

import com.fitnessclub.DOMAIN.Angajat;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AngajatRepository extends PagingAndSortingRepository<Angajat,Long> {
}
