package jrm.med.voll.JRMApiMedVoll.dto;

import jakarta.validation.constraints.NotNull;
import jrm.med.voll.JRMApiMedVoll.models.MotivoCancelamento;

public record DadosExclusaoConsulta(
        @NotNull
        Long idConsulta,
        @NotNull
        MotivoCancelamento motivoCancelamento
) {
}
