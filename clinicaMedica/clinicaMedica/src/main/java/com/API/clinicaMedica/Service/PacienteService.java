package com.API.clinicaMedica.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.API.clinicaMedica.Model.PacienteModel;
import com.API.clinicaMedica.Repository.PacienteRepository;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository repository;

    public PacienteModel Salvar(PacienteModel paciente) {
        return repository.save(paciente);
    }


    public List<PacienteModel> ListarTodos() {
        return repository.findAll();
    }

    
    public PacienteModel BuscarPorId(Long id) {
        Optional<PacienteModel> paciente = repository.findById(id);
        if (paciente.isPresent()) {
            return paciente.get();
        } else {
            return null;
        }
    }
       

         public PacienteModel Atualizar(Long id, PacienteModel paciente) {
    PacienteModel pacienteExistente = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Paciente não encontrado com o ID: " + id));

    pacienteExistente.setNome(paciente.getNome());
    pacienteExistente.setCpf(paciente.getCpf());

    return repository.save(pacienteExistente);

  }

    public void Deletar(Long id) {
        repository.deleteById(id);
    }

    
}
