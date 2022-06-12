package com.fitnessclub.DOMAIN;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@Entity
public class Produs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Denumirea produsului nu poate fi null!")
    private String denumire;
    @NotBlank(message = "Aroma produsului nu poate fi null!")
    private String aroma;
    @NotNull(message = "Gramajul produsului nu poate fi null!")
    private float gramaj;
    @NotNull(message = "Pretul produsului nu poate fi null!")
    private float pret;
    @NotBlank(message = "Descrierea produsului nu poate fi null!")
    private String descriere;
    @Lob
    private Byte[] image;


    @OneToMany(mappedBy = "produs",cascade = CascadeType.ALL)
    private List<Detalii_comanda> detalii_comanda;



    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL)
    private List<Cos> cosList;
/*
    @ManyToMany(mappedBy = "prods",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
    private List<Comanda> comandas;


 */
}
