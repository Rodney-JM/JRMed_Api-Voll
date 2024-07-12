package jrm.med.voll.JRMApiMedVoll.repository;

import jrm.med.voll.JRMApiMedVoll.dto.DadosCadastroMedico;
import jrm.med.voll.JRMApiMedVoll.dto.DadosCadastroPaciente;
import jrm.med.voll.JRMApiMedVoll.dto.DadosEndereco;
import jrm.med.voll.JRMApiMedVoll.models.Consulta;
import jrm.med.voll.JRMApiMedVoll.models.Especialidade;
import jrm.med.voll.JRMApiMedVoll.models.Medico;
import jrm.med.voll.JRMApiMedVoll.models.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest //Testar uma interface
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //nao procura um banco de dados em memória
@ActiveProfiles("test") //le o application -test
class MedicoRepositoryTest {
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deve devolver null quando unico medico cadastrado nao está disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario1() {
        //given ou arrange
        LocalDateTime proximaTerca = LocalDateTime.now()
                .with(TemporalAdjusters.next(DayOfWeek.TUESDAY))
                .withHour(10)
                .withMinute(8)
                .withSecond(0)
                .withNano(0);

        cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);

        //when ou act
        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivre(Especialidade.ORTOPEDIA, proximaTerca);

        //then ou assert
        assertThat(medicoLivre).isNull();
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consulta(null, medico, paciente, data));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private DadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosCadastroMedico(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosCadastroPaciente dadosPaciente(String nome, String email, String cpf) {
        return new DadosCadastroPaciente(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}