package jrm.med.voll.JRMApiMedVoll.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jrm.med.voll.JRMApiMedVoll.models.Especialidade;

//@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosCadastroMedico(
        @NotBlank // verifica se o campo não está vazio e nulo
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")// são de 4 a seis dígitos
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull
        @Valid
        DadosEndereco endereco
) {
}
