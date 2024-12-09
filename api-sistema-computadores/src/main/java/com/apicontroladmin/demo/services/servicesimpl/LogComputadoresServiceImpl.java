package com.apicontroladmin.demo.services.servicesimpl;

import com.apicontroladmin.demo.domain.entidades.LogComputadores;
import com.apicontroladmin.demo.domain.repositories.LogComputadoresRepository;
import com.apicontroladmin.demo.services.servicesinterface.LogComputadoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogComputadoresServiceImpl implements LogComputadoresService {

    @Autowired
    LogComputadoresRepository logComputadoresRepository;

    @Override
    public void createLogAutomaticoComputadores(String mensagem, String mac, String nome_computador, String tipo) {

        LogComputadores log = new LogComputadores();

        if (tipo.equals("alterarnome")) {
            log.setMessage(mensagem);
            log.setMacVinculado(mac);
            log.setComputadorVinculado(nome_computador);
            log.setUserVinculado("Log automático");
            log.setNomeUser("log@alterarnome");
            log.setDatahora(LocalDateTime.now());
        } else if (tipo.equals("primeirocadastro")) {
            log.setMessage(mensagem);
            log.setMacVinculado(mac);
            log.setComputadorVinculado(nome_computador);
            log.setUserVinculado("Log automático");
            log.setNomeUser("log@primeirocadastro");
            log.setDatahora(LocalDateTime.now());
        } else {
            log.setMessage(mensagem);
            log.setMacVinculado(mac);
            log.setComputadorVinculado(nome_computador);
            log.setUserVinculado("Log automático");
            log.setNomeUser("log@automatico");
            log.setDatahora(LocalDateTime.now());
        }

        logComputadoresRepository.save(log);

    }

}
