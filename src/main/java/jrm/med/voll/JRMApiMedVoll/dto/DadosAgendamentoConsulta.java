package jrm.med.voll.JRMApiMedVoll.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jrm.med.voll.JRMApiMedVoll.models.Especialidade;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta (
        Long idMedico,
        @NotNull
        Long idPaciente,
        @NotNull
        @Future
        LocalDateTime data,


        Especialidade especialidade
){
}
