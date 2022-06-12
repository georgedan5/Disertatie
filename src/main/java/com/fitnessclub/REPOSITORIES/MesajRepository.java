package com.fitnessclub.REPOSITORIES;

import com.fitnessclub.DOMAIN.Mesaj;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesajRepository extends PagingAndSortingRepository<Mesaj,Long> {
}
