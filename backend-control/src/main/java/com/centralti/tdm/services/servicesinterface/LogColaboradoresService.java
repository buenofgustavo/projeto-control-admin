package com.centralti.tdm.services.servicesinterface;

import com.centralti.tdm.domain.usuarios.DTO.LogColaboradoresDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LogColaboradoresService {

    void createLogColaboradores(String tipo, String mensagem, String nome, String cpf, String mesAno, Integer dpid);
    List<LogColaboradoresDTO> findAll();
}


