package com.fitnessclub.DOMAIN;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.List;

import static com.fitnessclub.DOMAIN.Client.convertUtilToSql;

@Setter
@Getter
@Entity
public class Comanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_client;
    private String nume;
    private String prenume;
    private String email;
    private String telefon;
    @NotBlank(message = "Adresa comenzii nu poate fi null! Daca doriti ridicare personala completati campul cu RP")
    private String adresa;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private java.util.Date data_comanda=convertUtilToSql(new java.util.Date());
    private String status;
    private String observatie;
    private float totalC;

    @OneToMany(mappedBy = "comanda",orphanRemoval = true)
   // Detalii_comanda detalii_comanda;
    private List<Detalii_comanda> detalii_comanda;




/*
    @ManyToMany(//targetEntity = Produs.class,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "detaliu_comandaP",
            joinColumns = @JoinColumn(name = "id_comanda", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_produs", referencedColumnName = "id"))
    private List<Produs> prods;
 */

/*
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "orders_details",
            joinColumns = @JoinColumn(name = "id_order", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_product", referencedColumnName = "id"))
    private List<Cos> cosul;



 */

}
