package com.centralti.tdm.controllers;

import com.centralti.tdm.errors.ErrorResponses;
import com.centralti.tdm.services.servicesinterface.ColaboradorComVariaveisService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/colaboradores-com-variaveis")
public class ColaboradorComVariaveisController {

    @Autowired
    ColaboradorComVariaveisService colaboradorComVariaveisService;

    @GetMapping("listar-cpf/{cpf}/{mes}/{ano}")
    @Transactional
    public ResponseEntity findColaboradoresByCpf(@PathVariable String cpf, @PathVariable String mes, @PathVariable Integer ano) {
        try {
            var dados = colaboradorComVariaveisService.findByColaborador(cpf, mes, ano);
            return ResponseEntity.ok(dados);
        } catch (EntityNotFoundException e) {
            ErrorResponses errorResponses = new ErrorResponses(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponses);
        }
    }

    @GetMapping("listar/{mes}/{ano}")
    @Transactional
    public ResponseEntity findAllColaboradores(@PathVariable String mes, @PathVariable Integer ano) {
        try {
            var dados = colaboradorComVariaveisService.findAllColaboradores(mes, ano);
            return ResponseEntity.ok(dados);
        } catch (EntityNotFoundException e) {
            ErrorResponses errorResponses = new ErrorResponses(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponses);
        }
    }

    @GetMapping("listar-departamento/{departamento}/{mes}/{ano}")
    @Transactional
    public ResponseEntity findAllColaboradores(@PathVariable String departamento, @PathVariable String mes, @PathVariable Integer ano) {
        try {
            var dados = colaboradorComVariaveisService.findAllColaboradoresByDepartamento(departamento, mes, ano);
            return ResponseEntity.ok(dados);
        } catch (EntityNotFoundException e) {
            ErrorResponses errorResponses = new ErrorResponses(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponses);
        }
    }

}
