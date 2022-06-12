package com.fitnessclub.DOMAIN;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.fitnessclub.DOMAIN.Client.convertUtilToSql;

@Setter
@Getter
@Entity
public class Istoric_client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Numarul de kilograme nu poate fi null!")
    private Float greutatea;
    @NotNull(message = "Inaltimea nu poate fi null!")
    private Float inaltimea;
    @NotNull(message = "Varsta nu poate fi null!")
    private int varsta;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private java.util.Date data_inregistrare=convertUtilToSql(new java.util.Date());
    private long idc;


    @ManyToOne
    private Client clients;
}
