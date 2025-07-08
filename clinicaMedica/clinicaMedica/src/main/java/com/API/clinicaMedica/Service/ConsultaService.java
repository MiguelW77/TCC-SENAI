package com.API.clinicaMedica.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.API.clinicaMedica.Model.ConsultaModel;
import com.API.clinicaMedica.Repository.ConsultaRepository;


@Service
public class ConsultaService {
    
    @Autowired
    private ConsultaRepository repository;

     public ConsultaModel Salvar(ConsultaModel consulta) {
        return repository.save(consulta);
    }


    public List<ConsultaModel> ListarTodos() {
        return repository.findAll();
    }

    
    public ConsultaModel BuscarPorId(Long id) {
        Optional<ConsultaModel> consulta = repository.findById(id);
        if (consulta.isPresent()) {
            return consulta.get();
        } else {
            return null;
        }
    }
       

         public ConsultaModel Atualizar(Long id, ConsultaModel consulta) {
    ConsultaModel consultaExistente = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Consulta não encontrada com o ID: " + id));

    consultaExistente.setHoraConsulta(consulta.getHoraConsulta());
    consultaExistente.setDataConsulta(consulta.getDataConsulta());
    consultaExistente.setStatusConsulta(consulta.getStatusConsulta());
    consultaExistente.setEspecialidade(consulta.getEspecialidade());   
    consultaExistente.setConsulta(consulta.getConsulta());    

            return repository.save(consultaExistente);
    
  }

    public void Deletar(Long id) {
        repository.deleteById(id);
    }
    
}
