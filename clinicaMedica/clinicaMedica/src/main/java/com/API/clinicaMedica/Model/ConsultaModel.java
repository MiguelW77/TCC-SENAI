package com.API.clinicaMedica.Model;

import java.time.DateTimeException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ConsultaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;

    @Column(nullable = false)
    private DateTimeException dataConsulta;

    @ManyToOne
    @JoinColumn(name = "id_paciente", referencedColumnName = "id", nullable = false)
    private PacienteModel paciente;

}