package jrm.med.voll.JRMApiMedVoll.service.validations.agendamento;

import jrm.med.voll.JRMApiMedVoll.dto.DadosAgendamentoConsulta;
import jrm.med.voll.JRMApiMedVoll.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidarHorarioAntecedente implements ValidadorAgendamentoConsultas {
    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        var hora = LocalDateTime.now();

        var diferencaMinutos = Duration.between(hora, dataConsulta).toMinutes();

        if(diferencaMinutos<30){
            throw new ValidacaoException("A consulta deve ser agendada, no mínimo, com 30 minutos de antecedência.");
        }
    }
}
