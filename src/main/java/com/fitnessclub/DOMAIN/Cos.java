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
public class Cos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idu;
    private String denumire;
    private String aroma;
    private float gramaj;
    private float pret;
    @Lob
    private Byte[] image;
    @ManyToOne
    private Produs produsC;


}
