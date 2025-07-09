package com.API.clinicaMedica;

import com.API.clinicaMedica.Model.PacienteModel;
import com.API.clinicaMedica.Service.PacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PacienteServiceIntegrationTest {

    @Autowired
    private PacienteService pacienteService;

    PacienteModel criarPacienteValido() {
        PacienteModel paciente = new PacienteModel();
        paciente.setNome("Maria Oliveira");
        paciente.setCpf("12345678900");
        paciente.setTelefone("11999998888");
        paciente.setEmail("maria@clinica.com");
        paciente.setSenha("senhaSegura");
        paciente.setDataNascimento(Date.valueOf(LocalDate.of(1985, 10, 15)));
        paciente.setGenero("Feminino");
        paciente.setEndereco("Rua da Paz, 123");
        paciente.setCep("04567890");
        return paciente;
    }

    @Test
    void testCadastroEBusca() {
        PacienteModel paciente = criarPacienteValido();
        PacienteModel salvo = pacienteService.Salvar(paciente);
        assertNotNull(salvo.getId());  
        assertEquals("Maria Oliveira", salvo.getNome());

        PacienteModel buscado = pacienteService.BuscarPorId(salvo.getId());
        assertNotNull(buscado);
        assertEquals("Feminino", buscado.getGenero());
    }

    @Test
    void testListagem() {
        pacienteService.Salvar(criarPacienteValido());
        List<PacienteModel> pacientes = pacienteService.ListarTodos();
        assertFalse(pacientes.isEmpty(), "A lista de pacientes não deve estar vazia");
    }

    @Test
    void testAtualizacao() {
        PacienteModel salvo = pacienteService.Salvar(criarPacienteValido());
        salvo.setNome("Maria Atualizada");
        salvo.setCpf("00011122233");

        PacienteModel atualizado = pacienteService.Atualizar(salvo.getId(), salvo);
        assertEquals("Maria Atualizada", atualizado.getNome());
        assertEquals("00011122233", atualizado.getCpf());
    }

    @Test
    void testExclusao() {
        PacienteModel salvo = pacienteService.Salvar(criarPacienteValido());
        Long id = salvo.getId();
        pacienteService.Deletar(id);

        PacienteModel excluido = pacienteService.BuscarPorId(id);
        assertNull(excluido, "Paciente deveria ter sido excluído");
    }

    @Test
    void testValidacaoCamposObrigatorios() {
        PacienteModel paciente = new PacienteModel();
        Exception excecao = assertThrows(Exception.class, () -> {
            pacienteService.Salvar(paciente);
        }, "Esperado erro ao tentar salvar paciente com campos obrigatórios vazios");

        System.out.println("[ASSERT] Erro esperado: " + excecao.getMessage());
    }
}
