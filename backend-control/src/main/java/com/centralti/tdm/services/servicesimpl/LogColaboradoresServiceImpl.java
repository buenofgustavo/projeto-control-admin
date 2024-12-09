package com.centralti.tdm.services.servicesimpl;

import com.centralti.tdm.domain.usuarios.DTO.LogColaboradoresDTO;
import com.centralti.tdm.domain.usuarios.entidades.LogColaboradores;
import com.centralti.tdm.domain.usuarios.repositories.LogColaboradoresRepository;
import com.centralti.tdm.services.servicesinterface.LogColaboradoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogColaboradoresServiceImpl implements LogColaboradoresService {

    @Autowired
    LogColaboradoresRepository logColaboradoresRepository;

    @Override
    public void createLogColaboradores(String tipo, String mensagem, String nome, String cpf, String mesAno, Integer dpid) {
        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        LogColaboradores log = new LogColaboradores();

        log.setTipo(tipo);
        log.setMensagem(mensagem);
        log.setNomeColaborador(nome);
        log.setCpfColaborador(cpf);
        log.setUsuario(emailUsuario);
        log.setMesAno(mesAno);
        log.setDataHora(LocalDateTime.now());
        log.setDpid(dpid);

        logColaboradoresRepository.save(log);
    }

    @Override
    public List<LogColaboradoresDTO> findAll() {
        List<LogColaboradores> chatMessages = logColaboradoresRepository.findAll();
        return chatMessages.stream()
                .map(LogColaboradoresDTO::new)
                .collect(Collectors.toList());
    }

}
