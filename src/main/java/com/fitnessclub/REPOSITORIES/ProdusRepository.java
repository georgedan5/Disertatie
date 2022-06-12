package com.fitnessclub.REPOSITORIES;

import com.fitnessclub.DOMAIN.Produs;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdusRepository extends PagingAndSortingRepository<Produs,Long> {
}
