package com.centralti.tdm.controllers;

import com.centralti.tdm.domain.usuarios.DTO.VariaveisColaboradoresDTO;
import com.centralti.tdm.errors.ErrorResponses;
import com.centralti.tdm.services.servicesinterface.VariaveisColaboradoresService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/variaveis")
public class VariaveisColaboradoresController {

    @Autowired
    VariaveisColaboradoresService variaveisColaboradoresService;

    @GetMapping("listar/{cpf}/{mes}/{ano}")
    public ResponseEntity findByVariaveis(@PathVariable String cpf, @PathVariable String mes, @PathVariable Integer ano) {
        try {
            var dados = variaveisColaboradoresService.FindVariavel(cpf, mes, ano);
            return ResponseEntity.ok(dados);
        } catch (EntityNotFoundException e) {
            ErrorResponses errorResponses = new ErrorResponses(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponses);
        }
    }

    @PostMapping("cadastrar")
    public ResponseEntity createVariaveis(@RequestBody VariaveisColaboradoresDTO variaveisColaboradoresDTO) {
        try {
            System.out.println(variaveisColaboradoresDTO);
            variaveisColaboradoresService.createdVariaveis(variaveisColaboradoresDTO);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            ErrorResponses errorResponses = new ErrorResponses(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponses);
        }
    }

    @PutMapping("update")
    public ResponseEntity updateVariaveis(@RequestBody VariaveisColaboradoresDTO variaveisColaboradoresDTO) {
        try {
            System.out.println(variaveisColaboradoresDTO);
            variaveisColaboradoresService.updateVariaveis(variaveisColaboradoresDTO);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            ErrorResponses errorResponses = new ErrorResponses(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponses);
        }
    }

}