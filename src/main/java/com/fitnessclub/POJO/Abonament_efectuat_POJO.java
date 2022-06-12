package com.fitnessclub.POJO;

import com.fitnessclub.DOMAIN.Abonament;
import com.fitnessclub.DOMAIN.Client;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import static com.fitnessclub.DOMAIN.Client.convertUtilToSql;

@Setter
@Getter

public class Abonament_efectuat_POJO {


    private Long id;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private java.util.Date data_inceput=convertUtilToSql(new java.util.Date());
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private java.util.Date data_expirare;
    long ida;
    private String emailc;
}
