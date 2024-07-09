package jrm.med.voll.JRMApiMedVoll.dto;

import jrm.med.voll.JRMApiMedVoll.models.Endereco;
import jrm.med.voll.JRMApiMedVoll.models.Paciente;

public record DadosDetalhamentoPaciente (
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        Endereco endereco
){
    public DadosDetalhamentoPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
