package com.fitnessclub.REPOSITORIES;

import com.fitnessclub.DOMAIN.Rol;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends PagingAndSortingRepository<Rol,Long> {
}
