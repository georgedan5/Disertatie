package com.fitnessclub.DOMAIN;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Setter
@Getter
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Numele clientului nu poate fi null!")
    private String nume;
    @NotBlank(message = "Prenumele clientului nu poate fi null!")
    private String prenume;
    @NotBlank(message = "Numarul de telefon al clientului nu poate fi null!")
    private String telefon;
    @NotBlank(message = "Email-ul clientului nu poate fi null!")
    @Column(unique=true)
    private String email;
    @NotBlank(message = "Sexul clientului nu poate fi null!")
    private String sex;
    @NotBlank(message = "Parola clientului nu poate fi null!")
    private String parola;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    @NotNull(message = "Data inregistrarii clientului nu poate fi null!")
    private java.util.Date data_inregistrare=convertUtilToSql(new java.util.Date());

    @OneToOne(mappedBy = "client",orphanRemoval = true)
    Abonament_efectuat abonament_efectuat;

    @OneToMany(mappedBy = "clients",cascade = CascadeType.ALL)
    private List<Istoric_client> istoric_client;

   // @OneToOne
   // private Utilizator utilizator;

    @OneToMany(mappedBy = "clientm",cascade = CascadeType.ALL)
    private List<Mesaj> mesaje;

    @OneToOne(mappedBy = "cli",orphanRemoval = true)
    Utilizator utilizator;

    public static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
}
