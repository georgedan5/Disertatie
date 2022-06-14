package com.fitnessclub.DOMAIN;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.fitnessclub.DOMAIN.Client.convertUtilToSql;

@Setter
@Getter
@Entity
public class Angajat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Numele angajatului nu poate fi null!")
    private String nume;
    @NotBlank(message = "Prenumele angajatului nu poate fi null!")
    private String prenume;
    @NotBlank(message = "Prenumele angajatului nu poate fi null!")
    private String functie;
    @NotBlank(message = "Functia angajatului nu poate fi null!")
    private String telefon;
    @NotBlank(message = "Email-ul angajatului nu poate fi null!")
    private String email;
    @NotBlank(message = "Parola angajatului nu poate fi null!")
    private String parola;
    @NotBlank(message = "Descrierea angajatului nu poate fi null!")
    private String descriere;
    @Min(value=40,message="Greutatea trebuie sa fie de cel putin 40 de Kg")
    @NotNull(message = "Greutatea angajatului nu poate fi null!")
    private double greutate;
    @Min(value=140,message="Inaltimea trebuie sa fie de cel putin 140 de cm")
    @NotNull(message = "Inaltimea angajatului nu poate fi null!")
    private double inaltime;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    @NotNull(message = "Data angajarii a angajatului nu poate fi null!")
    private java.util.Date data_angajare=convertUtilToSql(new java.util.Date());
    @Lob
    private Byte[] image;
}
