package jrm.med.voll.JRMApiMedVoll.service;

import jrm.med.voll.JRMApiMedVoll.dto.DadosCadastroMedico;
import jrm.med.voll.JRMApiMedVoll.models.Medico;
import jrm.med.voll.JRMApiMedVoll.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository repository;

    public void cadastrar(DadosCadastroMedico dados){
        repository.save(new Medico(dados));
    }
}
