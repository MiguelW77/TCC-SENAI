package com.API.clinicaMedica.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.API.clinicaMedica.Model.MedicoModel;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoModel, Long> {
    // Aqui você pode adicionar métodos personalizados, se necessário
    // Por exemplo, para buscar médicos por especialidade, nome, etc.
    // List<MedicoModel> findByEspecialidade(String especialidade);
    // Optional<MedicoModel> findByNome(String nome);
    
}
