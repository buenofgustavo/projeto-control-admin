package com.centralti.tdm.domain.usuarios.DTO;

import com.centralti.tdm.domain.usuarios.entidades.LogColaboradores;

import java.time.LocalDateTime;

public record LogColaboradoresDTO
        (
                String tipo,
                String mensagem,
                String nomeColaborador,
                String cpfColaborador,
                String usuario,
                LocalDateTime dataHora,
                String mesAno,
                Integer dpid

        ) {

    public LogColaboradoresDTO(LogColaboradores logColaboradores){
        this(logColaboradores.getTipo(), logColaboradores.getMensagem(),
                logColaboradores.getNomeColaborador(), logColaboradores.getCpfColaborador(),
                logColaboradores.getUsuario(), logColaboradores.getDataHora(), logColaboradores.getMesAno(),
                logColaboradores.getDpid()
        );
    }
}
