package jrm.med.voll.JRMApiMedVoll.service.validations.cancelamento;

import jrm.med.voll.JRMApiMedVoll.dto.DadosExclusaoConsulta;
import jrm.med.voll.JRMApiMedVoll.infra.exception.ValidacaoException;
import jrm.med.voll.JRMApiMedVoll.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidarAntecedenteMinimoExclusao implements ValidadorCancelamentoConsultas{
    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DadosExclusaoConsulta dados) {
        var consulta = repository.getReferenceById(dados.idConsulta());

        var tempoConsulta = consulta.getData();
        var agora = LocalDateTime.now();

        var diferencaHoras = Duration.between(agora, tempoConsulta).toHours();

        if(diferencaHoras < 24){
            throw new ValidacaoException("é preciso de uma antecedência mínima de 24 horas para excluir uma consulta");
        }
    }
}
