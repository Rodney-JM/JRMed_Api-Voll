package jrm.med.voll.JRMApiMedVoll.service;

import jrm.med.voll.JRMApiMedVoll.dto.DadosAtualizacaoMedico;
import jrm.med.voll.JRMApiMedVoll.dto.DadosCadastroMedico;
import jrm.med.voll.JRMApiMedVoll.dto.MedicoDTO;
import jrm.med.voll.JRMApiMedVoll.models.Medico;
import jrm.med.voll.JRMApiMedVoll.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository repository;

    public void cadastrar(DadosCadastroMedico dados){

        repository.save(new Medico(dados));
    }

    public Page<MedicoDTO> mostrarTodosMedicos(Pageable page){
        return repository.findAllByAtivoTrue(page)
                .map(m -> new MedicoDTO(m.getId(),m.getNome(), m.getEmail(), m.getCrm(), m.getEspecialidade()));
    }

    public void atualizarMedico(DadosAtualizacaoMedico dados){
        var medico = repository.findById(dados.id());
        medico.get().atualizarInformacoes(dados);
    }

    public void deletarMedico(Long id){
        var medico = repository.findById(id);
        medico.get().excluir();
    }
}
