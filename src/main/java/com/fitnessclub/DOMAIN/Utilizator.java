package com.fitnessclub.DOMAIN;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
public class Utilizator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Numele de utilizator nu poate fi null!")
    private String Username;
    @NotBlank(message = "Parola nu poate fi null!")
    private String Parola;
    @NotNull
    private Long Status;
    //@OneToOne(mappedBy = "utilizator",orphanRemoval = true)
   // Client client;

    @OneToOne
    Angajat ang;

    @OneToOne
    Client cli;
}
