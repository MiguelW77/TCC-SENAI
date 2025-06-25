package com.API.clinicaMedica.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.API.clinicaMedica.Model.PacienteModel;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteModel, Long> {

    // Aqui você pode adicionar métodos personalizados, se necessário
    // Por exemplo, para buscar pacientes por nome, CPF, etc.
    // List<PacienteModel> findByNome(String nome);
    // Optional<PacienteModel> findByCpf(String cpf);
    // Optional<PacienteModel> findByEmail(String email);
}
