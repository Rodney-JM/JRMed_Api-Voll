package jrm.med.voll.JRMApiMedVoll.service;

import jrm.med.voll.JRMApiMedVoll.dto.DadosAgendamentoConsulta;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {
    public DadosAgendamentoConsulta agendar(DadosAgendamentoConsulta dados){
        return new DadosAgendamentoConsulta(null, null, null );
    }
}
