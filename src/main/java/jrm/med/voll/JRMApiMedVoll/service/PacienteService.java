package jrm.med.voll.JRMApiMedVoll.service;

import jrm.med.voll.JRMApiMedVoll.dto.DadosAtualizacaoPaciente;
import jrm.med.voll.JRMApiMedVoll.dto.DadosCadastroPaciente;
import jrm.med.voll.JRMApiMedVoll.dto.PacienteDTO;
import jrm.med.voll.JRMApiMedVoll.models.Paciente;
import jrm.med.voll.JRMApiMedVoll.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository repository;

    public Paciente cadastrar(DadosCadastroPaciente dados){
        var paciente = new Paciente(dados);
        repository.save(paciente);

        return paciente;
    }

    public Page<PacienteDTO> mostrePacientes(Pageable page){
        return repository.findAllByAtivoTrue(page)
                .map(p -> new PacienteDTO(p.getId(),p.getNome(), p.getEmail(), p.getCpf()));
    }

    public Paciente atualizar(DadosAtualizacaoPaciente dados){
        var paciente = repository.findById(dados.id());
        paciente.get().atualizarInformacoes(dados);

        return paciente.get();
    }

    public void inativar(Long id){
        var paciente = repository.findById(id);
        paciente.get().inativar();
    }

    public Paciente detalhar(Long id){
        var paciente = repository.getReferenceById(id);
        return paciente;
    }
}
