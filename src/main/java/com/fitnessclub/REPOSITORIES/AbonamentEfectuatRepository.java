package com.fitnessclub.REPOSITORIES;

import com.fitnessclub.DOMAIN.Abonament_efectuat;
import com.fitnessclub.DOMAIN.Cos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbonamentEfectuatRepository extends JpaRepository<Abonament_efectuat, Long> {
   ///Abonament_efectuat findByclient_id(long id);
   List<Abonament_efectuat> findByclient_id(long id);
}
