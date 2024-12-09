package com.centralti.tdm.controllers;

import com.centralti.tdm.domain.usuarios.DTO.CombinacaoSalarialDTO;
import com.centralti.tdm.errors.ErrorResponses;
import com.centralti.tdm.services.servicesinterface.CombinacaoSalarialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/combinacao-salarial")
public class CombinacaoSalarialController {

    @Autowired
    CombinacaoSalarialService combinacaoSalarialService;

    @PostMapping("cadastrar")
    public ResponseEntity createAcessos(@RequestBody CombinacaoSalarialDTO combinacaoSalarialDTO) {
        try {
            System.out.println(combinacaoSalarialDTO);
            combinacaoSalarialService.createdCombinacao(combinacaoSalarialDTO);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            ErrorResponses errorResponses = new ErrorResponses(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponses);
        }
    }

}
