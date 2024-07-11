package jrm.med.voll.JRMApiMedVoll.service.validations;

import jrm.med.voll.JRMApiMedVoll.dto.DadosAgendamentoConsulta;
import jrm.med.voll.JRMApiMedVoll.infra.exception.ValidacaoException;

import java.time.DayOfWeek;

public class ValidarHorarioFuncionamentoClinica {

    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesAberturaClinica = dataConsulta.getHour() < 7;
        var depoisEncerramentoClinica = dataConsulta.getHour() > 18;

        if(domingo || antesAberturaClinica || depoisEncerramentoClinica){
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica!");
        }
    }
}
