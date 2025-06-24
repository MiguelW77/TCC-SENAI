package com.API.clinicaMedica.Model;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
 import  jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter

@Table(name = "medico")

@NoArgsConstructor
public class MedicoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 50)
    private String especialidade;

    @Column(length = 15, nullable = false)
    private String crm;

    @Column(length = 15, nullable = false)
    private String telefone;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String senha;

    @Column(nullable = false)
    private String termos;

    
}