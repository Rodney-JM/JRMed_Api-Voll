package jrm.med.voll.JRMApiMedVoll.models;
import jakarta.persistence.Embeddable;
import jrm.med.voll.JRMApiMedVoll.dto.DadosEndereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(DadosEndereco endereco) {
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
        this.cidade = endereco.cidade();
        this.uf = endereco.uf();
    }

    public void atualizarInformacoes(DadosEndereco endereco){
        this.logradouro = (endereco.logradouro()!=null)?endereco.logradouro(): logradouro;
        this.bairro = (endereco.bairro()!=null)?endereco.bairro():bairro;
        this.cep = (endereco.cep()!=null)?endereco.cep():cep;
        this.numero = (endereco.numero()!=null)?endereco.numero():numero;
        this.complemento = (endereco.complemento()!=null)?endereco.complemento():complemento;
        this.cidade = (endereco.cidade()!=null)?endereco.cidade():cidade;
        this.uf = (endereco.uf()!=null)?endereco.uf():uf;
    }
}
