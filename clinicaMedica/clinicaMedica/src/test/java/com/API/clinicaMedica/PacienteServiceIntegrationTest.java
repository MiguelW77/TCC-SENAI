package com.API.clinicaMedica;

import com.API.clinicaMedica.Model.PacienteModel;
import com.API.clinicaMedica.Service.PacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PacienteServiceIntegrationTest {

    @Autowired
    private PacienteService pacienteService;

    /*
     * Nome: Teste de Cadastro de Paciente
     * Objetivo: Validar se o sistema cadastra e busca paciente corretamente.
     */
    @Test
    void testSalvarEBuscarPaciente() {
        System.out.println("[TESTE] Iniciando teste de salvar e buscar paciente...");

        PacienteModel paciente = new PacienteModel();
        paciente.setNome("João da Silva");
        paciente.setCpf("12345678900");
        paciente.setTelefone("11988887777");
        paciente.setEmail("joao.silva@clinica.com");
        paciente.setSenha("senha123");
        paciente.setTermos("aceito");
        paciente.setDataNascimento(Date.valueOf(LocalDate.of(1990, 5, 20)));
        paciente.setGenero("Masculino");
        paciente.setEndereco("Rua das Flores, 100");
        paciente.setCep("01234567");

        System.out.println("[TESTE] Tentando salvar paciente: " + paciente.getNome());

        PacienteModel salvo = pacienteService.Salvar(paciente);

        assertNotNull(salvo.getId(), "[ASSERT] O ID não deve ser nulo");
        assertEquals("João da Silva", salvo.getNome(), "[ASSERT] Nome deve ser 'João da Silva'");

        PacienteModel encontrado = pacienteService.BuscarPorId(salvo.getId());

        assertNotNull(encontrado, "[ASSERT] O paciente buscado não deve ser nulo");
        assertEquals("Masculino", encontrado.getGenero(), "[ASSERT] Gênero deve ser 'Masculino'");
        assertEquals("01234567", encontrado.getCep(), "[ASSERT] CEP deve ser '01234567'");

        System.out.println("[TESTE] Teste finalizado com sucesso!");
    }
}
