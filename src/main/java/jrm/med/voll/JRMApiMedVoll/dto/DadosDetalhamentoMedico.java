package jrm.med.voll.JRMApiMedVoll.dto;

import jrm.med.voll.JRMApiMedVoll.models.Endereco;
import jrm.med.voll.JRMApiMedVoll.models.Especialidade;
import jrm.med.voll.JRMApiMedVoll.models.Medico;

public record DadosDetalhamentoMedico (
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade,
        Endereco endereco
){

    public DadosDetalhamentoMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}
