package jrm.med.voll.JRMApiMedVoll.controller;

import jakarta.validation.Valid;
import jrm.med.voll.JRMApiMedVoll.dto.DadosAtualizacaoMedico;
import jrm.med.voll.JRMApiMedVoll.dto.DadosAtualizacaoPaciente;
import jrm.med.voll.JRMApiMedVoll.dto.DadosCadastroPaciente;
import jrm.med.voll.JRMApiMedVoll.dto.PacienteDTO;
import jrm.med.voll.JRMApiMedVoll.models.Paciente;
import jrm.med.voll.JRMApiMedVoll.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
    @Autowired
    private PacienteService service;

    @PostMapping
    @Transactional
    public void cadastrarPaciente(@RequestBody @Valid DadosCadastroPaciente dadosPaciente){
        service.cadastrar(dadosPaciente);
    }

    @GetMapping
    public Page<PacienteDTO> mostrarPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable page){
        return service.mostrePacientes(page);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados){
        service.atualizar(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void inativar(@PathVariable Long id){
        service.inativar(id);
    }
}
