package jrm.med.voll.JRMApiMedVoll.service;

import jrm.med.voll.JRMApiMedVoll.dto.DadosAgendamentoConsulta;
import jrm.med.voll.JRMApiMedVoll.dto.DadosExclusaoConsulta;
import jrm.med.voll.JRMApiMedVoll.infra.exception.ValidacaoException;
import jrm.med.voll.JRMApiMedVoll.models.Consulta;
import jrm.med.voll.JRMApiMedVoll.models.Medico;
import jrm.med.voll.JRMApiMedVoll.repository.ConsultaRepository;
import jrm.med.voll.JRMApiMedVoll.repository.MedicoRepository;
import jrm.med.voll.JRMApiMedVoll.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void cancelar(DadosExclusaoConsulta dados){
        if(dados.motivoCancelamento() == null){
            throw new ValidacaoException("É obrigátorio informar o motivo do cancelamento");
        }

        if(!repository.existsById(dados.idConsulta())){
            throw new ValidacaoException("Informe um id de uma consulta válida");
        }

        var consulta = repository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivoCancelamento());
    }

    public void agendar(DadosAgendamentoConsulta dados){
        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Paciente não existem");
        }

        if(dados.idMedico()!=null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException(("Médico não existe"));
        }

        var paciente = pacienteRepository.findById(dados.idPaciente());
        var medico = selecionarMedico(dados);

        var consulta = new Consulta(null, medico, paciente.get(), dados.data(), null);

        repository.save(consulta);
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
