package com.API.clinicaMedica.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "prontuario")
@Getter
@Setter
@NoArgsConstructor
public class ProntuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String nome_medico;

    @Column(nullable = false, length = 15)
    private String crm_medico;

    @Column(nullable = false, length = 500)
    private String sintomas;

    @Column(nullable = false, length = 500)
    private String diagnostico;

    @Column(nullable = false)
    @Lob
    private String prescricao;
}
