package com.API.clinicaMedica.Model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import  jakarta.persistence.*;
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

    @Column(nullable = false, length = 11)
    private String cpf;

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

    @OneToMany(mappedBy = "medico")
    @JsonManagedReference
    private List<ConsultaModel> consultas;
    
    @OneToMany(mappedBy= "medico")
    @JsonManagedReference
    private List<ExamesModel> exames;
    
    @OneToMany(mappedBy = "medico")
    @JsonManagedReference
    private List<ProntuarioModel> prontuarios;

    @OneToMany(mappedBy = "medico")
    @JsonManagedReference
    private List<PacienteModel> pacientes;
}