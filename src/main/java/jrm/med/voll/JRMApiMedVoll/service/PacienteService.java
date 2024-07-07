package jrm.med.voll.JRMApiMedVoll.service;

import jrm.med.voll.JRMApiMedVoll.dto.DadosCadastroPaciente;
import jrm.med.voll.JRMApiMedVoll.models.Paciente;
import jrm.med.voll.JRMApiMedVoll.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository repository;

    public void cadastrar(DadosCadastroPaciente dados){
        repository.save(new Paciente(dados));
    }
}
