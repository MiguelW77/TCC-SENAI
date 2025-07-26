package com.API.clinicaMedica.DTOs;

import java.time.LocalDate;
import java.time.LocalTime;

import com.API.clinicaMedica.Model.ConsultaModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ConsultaDTO {
    private LocalDate data;
    private LocalTime hora;
    private String nomePaciente;
    private String nomeMedico;


    public ConsultaDTO(ConsultaModel model){
        this.data = model.getDataConsulta();
        this.hora = model.getHoraConsulta();
        this.nomePaciente = model.getPaciente().getNome();
        this.nomeMedico = model.getMedico().getNome();
        
    }
    
    

}
