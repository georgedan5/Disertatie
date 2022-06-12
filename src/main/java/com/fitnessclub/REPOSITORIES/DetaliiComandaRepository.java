package com.fitnessclub.REPOSITORIES;


import com.fitnessclub.DOMAIN.Cos;
import com.fitnessclub.DOMAIN.Detalii_comanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetaliiComandaRepository extends JpaRepository<Detalii_comanda, Long> {
    List<Detalii_comanda> findByComanda_Id(long id);
}
