package com.fitnessclub.DOMAIN;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
public class Abonament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Denumirea abonamentului nu poate fi null!")
    private String denumire;
    @NotNull(message = "Pretul abonamentului nu poate fi null!")
    private float pret;
    @NotNull(message = "Descrierea abonamentului nu poate fi null!")
    private String descriere;

    @OneToOne(mappedBy = "abonament",orphanRemoval = true)
    Abonament_efectuat abonament_efectuat;


    @OneToOne(mappedBy = "ang",orphanRemoval = true)
    Utilizator utilizator;


}
