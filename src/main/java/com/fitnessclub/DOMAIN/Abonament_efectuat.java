package com.fitnessclub.DOMAIN;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.List;

import static com.fitnessclub.DOMAIN.Client.convertUtilToSql;

@Setter
@Getter
@Entity
public class Abonament_efectuat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private java.util.Date data_inceput=convertUtilToSql(new java.util.Date());
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private java.util.Date data_expirare;


    @OneToOne
    Client client;
    @OneToOne
    Abonament abonament;

}
