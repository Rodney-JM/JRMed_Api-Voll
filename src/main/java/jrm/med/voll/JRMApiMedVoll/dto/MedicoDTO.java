package jrm.med.voll.JRMApiMedVoll.dto;

import jrm.med.voll.JRMApiMedVoll.models.Especialidade;

public record MedicoDTO (
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
){
}
