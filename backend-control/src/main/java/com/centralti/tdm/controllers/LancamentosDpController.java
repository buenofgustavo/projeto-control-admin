package com.centralti.tdm.controllers;

import com.centralti.tdm.domain.usuarios.DTO.LancamentoDpDTO;
import com.centralti.tdm.domain.usuarios.entidades.LancamentoDp;
import com.centralti.tdm.errors.ErrorResponses;
import com.centralti.tdm.services.servicesinterface.LancamentoDpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/importacao-dp")
public class LancamentosDpController {

    @Autowired
    LancamentoDpService lancamentoDpService;

    @PostMapping("/{mes}/{ano}")
    public ResponseEntity importacaoLancamentoDp(@RequestParam("file") MultipartFile files, @PathVariable String mes, @PathVariable Integer ano) {
        try {
            lancamentoDpService.importacaoLancamentosDp(files, mes, ano);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            ErrorResponses errorResponses = new ErrorResponses(e.getMessage());
            System.out.println("ERRO");
            System.out.println(e);
            return ResponseEntity.badRequest().body(errorResponses);
        }
    }

    @PostMapping("aprovar")
    public ResponseEntity aprovacaoLancamento(@RequestBody LancamentoDpDTO lancamentoDpDTO) {
        try {
            lancamentoDpService.atualizacaoLancamentosDp(lancamentoDpDTO);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            ErrorResponses errorResponses = new ErrorResponses(e.getMessage());
            System.out.println("ERRO");
            System.out.println(e);
            return ResponseEntity.badRequest().body(errorResponses);
        }
    }

}
