package com.apicontroladmin.demo.controllers;

import com.apicontroladmin.demo.domain.DTO.ComputadoresDTO;
import com.apicontroladmin.demo.errors.ErrorResponses;
import com.apicontroladmin.demo.services.servicesinterface.TransactionalComputadoresService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/computadores")
public class ComputadoresController {

    @Autowired
    TransactionalComputadoresService transactionalComputadoresService;

    @PostMapping()
    public ResponseEntity criarComputadores(@RequestBody @Valid ComputadoresDTO computadoresDTO){
        try {
            transactionalComputadoresService.criarComputadores(computadoresDTO);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            ErrorResponses errorResponses = new ErrorResponses(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponses);
        }
    }

}
