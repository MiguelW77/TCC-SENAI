package com.API.clinicaMedica.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.API.clinicaMedica.Model.ConsultaModel;

public interface ConsultaRepository extends JpaRepository<ConsultaModel, Long>{
    
}