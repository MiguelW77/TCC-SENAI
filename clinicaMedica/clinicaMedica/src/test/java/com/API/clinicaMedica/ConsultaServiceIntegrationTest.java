package com.API.clinicaMedica;

import com.API.clinicaMedica.Model.ConsultaModel;
import com.API.clinicaMedica.Model.PacienteModel;
import com.API.clinicaMedica.Service.ConsultaService;
import com.API.clinicaMedica.Service.PacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ConsultaServiceIntegrationTest {

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private PacienteService pacienteService;

    private PacienteModel criarPacienteDeTeste() {
        PacienteModel paciente = new PacienteModel();
        paciente.setNome("Maria Teste");
        paciente.setCpf("11122233344");
        paciente.setTelefone("11999998888");
        paciente.setEmail("maria@teste.com");
        paciente.setSenha("senha");
        paciente.setTermos("aceito");
        paciente.setDataNascimento(Date.valueOf(LocalDate.of(1995, 3, 15)));
        paciente.setGenero("Feminino");
        paciente.setEndereco("Rua Teste, 123");
        paciente.setCep("01010101");
        return pacienteService.Salvar(paciente);
    }

    @Test
    void testCadastrarEListarConsulta() {
        PacienteModel paciente = criarPacienteDeTeste();

        ConsultaModel consulta = new ConsultaModel();
        consulta.setDataConsulta("2025-08-15");
        consulta.setHoraConsulta("10:00");
        consulta.setConsulta("Consulta de rotina");
        consulta.setEspecialidade("Clínico Geral");
        consulta.setStatusConsulta("Agendada");
        consulta.setPaciente(paciente);

        ConsultaModel salva = consultaService.Salvar(consulta);

        assertNotNull(salva.getId());
        assertEquals("Agendada", salva.getStatusConsulta());

        ConsultaModel buscada = consultaService.BuscarPorId(salva.getId());
        assertEquals("Clínico Geral", buscada.getEspecialidade());

        List<ConsultaModel> todas = consultaService.ListarTodos();
        assertTrue(todas.size() > 0);
    }

    @Test
    void testAtualizarConsulta() {
        PacienteModel paciente = criarPacienteDeTeste();

        ConsultaModel consulta = new ConsultaModel();
        consulta.setDataConsulta("2025-07-15");
        consulta.setHoraConsulta("14:30");
        consulta.setConsulta("Exame de sangue");
        consulta.setEspecialidade("Hematologia");
        consulta.setStatusConsulta("Agendada");
        consulta.setPaciente(paciente);

        ConsultaModel salva = consultaService.Salvar(consulta);

        salva.setHoraConsulta("15:00");
        salva.setStatusConsulta("Confirmada");

        ConsultaModel atualizada = consultaService.Atualizar(salva.getId(), salva);

        assertEquals("15:00", atualizada.getHoraConsulta());
        assertEquals("Confirmada", atualizada.getStatusConsulta());
    }

    @Test
    void testDeletarConsulta() {
        PacienteModel paciente = criarPacienteDeTeste();

        ConsultaModel consulta = new ConsultaModel();
        consulta.setDataConsulta("2025-09-01");
        consulta.setHoraConsulta("11:00");
        consulta.setConsulta("Consulta teste exclusão");
        consulta.setEspecialidade("Pediatria");
        consulta.setStatusConsulta("Agendada");
        consulta.setPaciente(paciente);

        ConsultaModel salva = consultaService.Salvar(consulta);

        consultaService.Deletar(salva.getId());

        ConsultaModel deletada = consultaService.BuscarPorId(salva.getId());
        assertNull(deletada);
    }

    @Test
    void testValidacaoCamposObrigatorios() {
        PacienteModel paciente = criarPacienteDeTeste();

        ConsultaModel consultaInvalida = new ConsultaModel();
    
        consultaInvalida.setPaciente(paciente);

        assertThrows(Exception.class, () -> {
            consultaService.Salvar(consultaInvalida);
        });
    }
}
