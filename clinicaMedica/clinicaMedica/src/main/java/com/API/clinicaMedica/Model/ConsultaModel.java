package com.API.clinicaMedica.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "consulta")
@NoArgsConstructor
public class ConsultaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;

    @Column(nullable = false)
    private String dataConsulta;

    @ManyToOne
    @JoinColumn(name = "id_paciente", referencedColumnName = "id", nullable = false)
    private PacienteModel paciente;

    @Column(nullable = false)
    private String horaConsulta;

    @Column(nullable = false, length = 500)
    private String consulta;

    @Column(nullable = false, length = 50)
    private String especialidade;

    @Column(nullable = false, length = 50)
    private String statusConsulta;

}