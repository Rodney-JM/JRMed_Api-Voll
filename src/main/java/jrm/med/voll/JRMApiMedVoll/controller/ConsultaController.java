package jrm.med.voll.JRMApiMedVoll.controller;

import jakarta.validation.Valid;
import jrm.med.voll.JRMApiMedVoll.dto.DadosAgendamentoConsulta;
import jrm.med.voll.JRMApiMedVoll.dto.DadosDetalhamentoConsulta;
import jrm.med.voll.JRMApiMedVoll.dto.DadosExclusaoConsulta;
import jrm.med.voll.JRMApiMedVoll.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {
    @Autowired
    private ConsultaService service;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){
        service.agendar(dados);

        return ResponseEntity.ok(new DadosDetalhamentoConsulta(null, null, null, null));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosExclusaoConsulta dados){
        service.cancelar(dados);

        return ResponseEntity.noContent().build();
    }
}
