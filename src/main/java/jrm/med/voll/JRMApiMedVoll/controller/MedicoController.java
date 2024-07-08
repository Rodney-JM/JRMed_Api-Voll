package jrm.med.voll.JRMApiMedVoll.controller;

import jakarta.validation.Valid;
import jrm.med.voll.JRMApiMedVoll.dto.DadosAtualizacaoMedico;
import jrm.med.voll.JRMApiMedVoll.dto.DadosCadastroMedico;
import jrm.med.voll.JRMApiMedVoll.dto.MedicoDTO;
import jrm.med.voll.JRMApiMedVoll.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    private MedicoService service;

    @PostMapping
    @Transactional
    public void cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dadosMedico){
        service.cadastrar(dadosMedico);
    }

    @GetMapping
    public Page<MedicoDTO> mostraTodosMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable pag){
        return service.mostrarTodosMedicos(pag);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        service.atualizarMedico(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id){
        service.deletarMedico(id);
    }
}
