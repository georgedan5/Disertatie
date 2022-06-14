package com.fitnessclub.REPOSITORIES;

import com.fitnessclub.DOMAIN.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ClientRepository extends JpaRepository<Client, Long> {
//public interface ClientRepository extends PagingAndSortingRepository <Client,Long> {


     Client findByEmail(String email);
}
