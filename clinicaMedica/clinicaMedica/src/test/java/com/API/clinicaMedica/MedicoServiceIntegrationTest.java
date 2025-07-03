package com.API.clinicaMedica;

import com.API.clinicaMedica.Model.MedicoModel;
import com.API.clinicaMedica.Service.MedicoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MedicoServiceIntegrationTest {

   
    @Autowired
    private MedicoService medicoService;

    @Test
    void testSalvarMedico() {

        System.out.println("[TESTE] Iniciando teste completo de CRUD de Médico...");

        MedicoModel medico = new MedicoModel();
        medico.setNome("Dra. Ana Beatriz");
        medico.setEspecialidade("Dermatologia");
        medico.setCrm("SP654321");
        medico.setTelefone("11977776666");
        medico.setEmail("ana@clinica.com");
        medico.setSenha("senha123");
        medico.setTermos("aceito");

        MedicoModel salvo = medicoService.Salvar(medico);
        assertNotNull(salvo.getId());
        assertEquals("Dermatologia", salvo.getEspecialidade());
    }

    @Test
    void testListagemDeMedicos() {
        List<MedicoModel> lista = medicoService.ListarTodos();
        assertNotNull(lista);
        assertTrue(lista.size() >= 0); 
    }

    @Test
    void testAtualizarMedico() {
        MedicoModel medico = new MedicoModel();
        medico.setNome("Dr. Marcos Silva");
        medico.setEspecialidade("Ortopedia");
        medico.setCrm("SP000111");
        medico.setTelefone("11999990000");
        medico.setEmail("marcos@clinica.com");
        medico.setSenha("123456");
        medico.setTermos("aceito");

        MedicoModel salvo = medicoService.Salvar(medico);

        MedicoModel atualizacao = new MedicoModel();
        atualizacao.setNome("Dr. Marcos Souza");
        atualizacao.setEspecialidade("Neurologia");
        atualizacao.setCrm("SP000111");
        atualizacao.setTelefone(salvo.getTelefone());
        atualizacao.setEmail(salvo.getEmail());
        atualizacao.setSenha(salvo.getSenha());
        atualizacao.setTermos(salvo.getTermos());

        MedicoModel atualizado = medicoService.Atualizar(salvo.getId(), atualizacao);

        assertEquals("Dr. Marcos Souza", atualizado.getNome());
        assertEquals("Neurologia", atualizado.getEspecialidade());
    }

    @Test
    void testExcluirMedico() {
        MedicoModel medico = new MedicoModel();
        medico.setNome("Dra. Paula Mendes");
        medico.setEspecialidade("Ginecologia");
        medico.setCrm("SP999888");
        medico.setTelefone("11911112222");
        medico.setEmail("paula@clinica.com");
        medico.setSenha("senha789");
        medico.setTermos("aceito");

        MedicoModel salvo = medicoService.Salvar(medico);
        assertNotNull(salvo.getId());

        medicoService.Deletar(salvo.getId());
        MedicoModel excluido = medicoService.BuscarPorId(salvo.getId());

        assertNull(excluido);
    }

    @Test
    void testValidacaoCamposObrigatorios() {
        MedicoModel medico = new MedicoModel();
        medico.setCrm("SP888777");
        medico.setTelefone("11912341234");
        medico.setEmail("erro@clinica.com");
        medico.setSenha("123456");
        medico.setTermos("aceito");

        Exception excecao = assertThrows(Exception.class, () -> {
            medicoService.Salvar(medico);
        });

        String msg = excecao.getMessage().toLowerCase();
        assertTrue(msg.contains("not-null") || msg.contains("constraint") || msg.contains("null"), 
            "[ASSERT] Deve acusar erro de campo obrigatório");

            System.out.println("[TESTE] Teste completo de CRUD finalizado com sucesso!");
    }
}
