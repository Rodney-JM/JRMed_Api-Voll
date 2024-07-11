package jrm.med.voll.JRMApiMedVoll.dto;

import jrm.med.voll.JRMApiMedVoll.models.Consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta (
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime data
){
    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
