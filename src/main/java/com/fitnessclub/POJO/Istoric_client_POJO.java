package com.fitnessclub.POJO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static com.fitnessclub.DOMAIN.Client.convertUtilToSql;
@Setter
@Getter

public class Istoric_client_POJO {


    private Long id;
    @NotNull(message = "Numarul de kilograme nu poate fi null!")
    private Float greutatea;
    @NotNull(message = "Inaltimea nu poate fi null!")
    private Float inaltimea;
    @NotNull(message = "Varsta nu poate fi null!")
    private int varsta;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private java.util.Date data_inregistrare;
    private String emailc;
    private float calorii;
    private long idc;
}
