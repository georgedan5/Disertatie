package com.fitnessclub.REPOSITORIES;

import com.fitnessclub.DOMAIN.Cos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
//public interface CosRepository  extends PagingAndSortingRepository<Cos,Long> {

public interface CosRepository extends JpaRepository<Cos, Long> {
    List<Cos> findByIdu(long id);
    @Transactional
    Long deleteByIdu(long idu);
}
