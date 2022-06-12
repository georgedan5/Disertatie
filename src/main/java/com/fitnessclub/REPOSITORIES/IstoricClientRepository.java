package com.fitnessclub.REPOSITORIES;

import com.fitnessclub.DOMAIN.Istoric_client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IstoricClientRepository extends JpaRepository<Istoric_client, Long> {
    //public interface IstoricClientRepository extends PagingAndSortingRepository<Istoric_client, Long> {
    List<Istoric_client> findById(long id);
    List<Istoric_client> findByGreutatea(float greutatea);
    List<Istoric_client> findByIdc(long idc);
   //Page<Istoric_client> findByGreutatea(long greutatea,Pageable pageable);
  // Page<Istoric_client> findByGreutatea (int pageNumber, String sortField, String sortDirection,long greutatea);
}
