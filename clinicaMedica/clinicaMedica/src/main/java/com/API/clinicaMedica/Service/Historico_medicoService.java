package com.API.clinicaMedica.Service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.API.clinicaMedica.Model.Historico_medicoModel;
import com.API.clinicaMedica.Repository.Historico_medicoRepository;

@Service
public class Historico_medicoService {
    @Autowired
    private Historico_medicoRepository repository;

    public Historico_medicoModel Salvar(Historico_medicoModel historico_medico){
        return repository.save(historico_medico);
    }

    public Historico_medicoModel BuscarPorId(Long id){
        Optional<Historico_medicoModel> historico_medico = repository.findById(id);
        if (historico_medico.isPresent()) {
            return historico_medico.get();
        } else {
            return null;
        }
    }
    public Historico_medicoModel Atualizar(Long id, Historico_medicoModel historico_medico){
        Historico_medicoModel historico_medicoExistente = repository.findById(id)
         .orElseThrow(() -> new RuntimeException("Historico Medico não encontrada com o ID: " + id));
         historico_medicoExistente.setHorahistorico_medico(historico_medico.getHorahistorico_medico());
         historico_medicoExistente.setDatahistorico_medico(historico_medico.getDatahistorico_medico());
            return repository.save(historico_medicoExistente);
        }
        public void Deletar(Long id){
            repository.deleteById(id);
        }
}