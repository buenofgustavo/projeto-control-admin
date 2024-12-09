package com.apicontroladmin.demo.services.servicesinterface;

import org.springframework.stereotype.Service;

@Service
public interface LogComputadoresService {

    void createLogAutomaticoComputadores(String mensagem, String mac, String nome_computador, String tipo);


}
