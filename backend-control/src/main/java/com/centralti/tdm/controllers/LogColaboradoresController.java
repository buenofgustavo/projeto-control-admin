package com.centralti.tdm.controllers;

import com.centralti.tdm.domain.usuarios.DTO.LogColaboradoresDTO;
import com.centralti.tdm.errors.ErrorResponses;
import com.centralti.tdm.services.servicesinterface.LogColaboradoresService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/log-colaboradores")
public class LogColaboradoresController {

    @Autowired
    LogColaboradoresService logColaboradoresService;

    @PostMapping
    public ResponseEntity<?> createMessage(@RequestBody LogColaboradoresDTO logColaboradoresDTO) {
        try {
            logColaboradoresService.createLogColaboradores(logColaboradoresDTO.tipo(), logColaboradoresDTO.mensagem(), logColaboradoresDTO.nomeColaborador(), logColaboradoresDTO.cpfColaborador(), logColaboradoresDTO.mesAno(), logColaboradoresDTO.dpid());
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            ErrorResponses errorResponses = new ErrorResponses(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponses);
        }

    }

    @GetMapping()
    public ResponseEntity<List<LogColaboradoresDTO>> getAll() {
        try {
            List<LogColaboradoresDTO> messages = logColaboradoresService.findAll();
            return ResponseEntity.ok(messages);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}
