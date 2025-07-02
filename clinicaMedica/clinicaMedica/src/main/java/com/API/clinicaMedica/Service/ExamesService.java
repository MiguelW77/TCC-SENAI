package com.API.clinicaMedica.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.API.clinicaMedica.Model.ExamesModel;
import com.API.clinicaMedica.Repository.ExamesRepository;

@Service
public class ExamesService {
    @Autowired
    private ExamesRepository repository;

    public ExamesModel Salvar(ExamesModel exames){
        return repository.save(exames);
    }

    public List<ExamesModel> listarTodos(){
        return repository.findAll();
    }

    public ExamesModel buscarPorId(Long id){
        Optional<ExamesModel> exames = repository.findById(id);
        if (exames.isPresent()) {
            return exames.get();
        } else {
            return null;
        }
}

       public ExamesModel Atualizar(Long id, ExamesModel exames){
        ExamesModel examesModelExistente = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Exames não encontrados por ID: " + id));
        examesModelExistente.setData_exame(exames.getData_exame());
        examesModelExistente.setLocal_exame(exames.getLocal_exame());
        examesModelExistente.setStatus_exame(exames.getStatus_exame());
        examesModelExistente.setTipo_exame(exames.getTipo_exame());
        return repository.save(examesModelExistente);
       }
       public void Deletar(Long id){
         repository.deleteById(id);
       }
}