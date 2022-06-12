package com.fitnessclub.DOMAIN;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
public class Mesaj {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Numele  nu poate fi null!")
    private String nume;
    @NotBlank(message = "Email-ul  nu poate fi null!")
    private String email;
    @NotBlank(message = "Mesajul nu poate fi null!")
    private String msg;

    @ManyToOne
    private Client clientm;
}
