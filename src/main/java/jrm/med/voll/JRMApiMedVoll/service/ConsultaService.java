package jrm.med.voll.JRMApiMedVoll.service;

import jrm.med.voll.JRMApiMedVoll.dto.DadosAgendamentoConsulta;
import jrm.med.voll.JRMApiMedVoll.dto.DadosDetalhamentoConsulta;
import jrm.med.voll.JRMApiMedVoll.dto.DadosExclusaoConsulta;
import jrm.med.voll.JRMApiMedVoll.infra.exception.ValidacaoException;
import jrm.med.voll.JRMApiMedVoll.models.Consulta;
import jrm.med.voll.JRMApiMedVoll.models.Medico;
import jrm.med.voll.JRMApiMedVoll.repository.ConsultaRepository;
import jrm.med.voll.JRMApiMedVoll.repository.MedicoRepository;
import jrm.med.voll.JRMApiMedVoll.repository.PacienteRepository;
import jrm.med.voll.JRMApiMedVoll.service.validations.agendamento.ValidadorAgendamentoConsultas;
import jrm.med.voll.JRMApiMedVoll.service.validations.cancelamento.ValidadorCancelamentoConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoConsultas> validadores;

    @Autowired
    private List<ValidadorCancelamentoConsultas> validadorCancelamentoConsultas;

    public void cancelar(DadosExclusaoConsulta dados){
        if(dados.motivoCancelamento() == null){
            throw new ValidacaoException("É obrigátorio informar o motivo do cancelamento");
        }

        if(!repository.existsById(dados.idConsulta())){
            throw new ValidacaoException("Informe um id de uma consulta válida");
        }

        validadorCancelamentoConsultas.forEach(v -> v.validar(dados));

        var consulta = repository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivoCancelamento());
    }

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){
        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Paciente não existem");
        }

        if(dados.idMedico()!=null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException(("Médico não existe"));
        }

        validadores.forEach(v-> v.validar(dados));

        var paciente = pacienteRepository.findById(dados.idPaciente());
        var medico = selecionarMedico(dados);
        if(medico == null){
            throw new ValidacaoException("Não existe médico disponível nessa data");
        }

        var consulta = new Consulta(null, medico, paciente.get(), dados.data(), null);

        repository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico selecionarMedico(DadosAgendamentoConsulta dados){
        if(dados.idMedico() !=null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatória quando o médico não for escolhido corretamente.");
        }

        return medicoRepository.escolherMedicoAleatorioLivre(dados.especialidade(), dados.data());
    }
}
