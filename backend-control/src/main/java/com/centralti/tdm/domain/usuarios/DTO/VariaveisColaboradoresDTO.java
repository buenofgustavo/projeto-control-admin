package com.centralti.tdm.domain.usuarios.DTO;

import com.centralti.tdm.domain.usuarios.entidades.VariaveisColaboradores;

public record VariaveisColaboradoresDTO
        (
                String id,
                String cpf,
                String mes,
                Integer ano,
                Float prv,
                Float chat,
                Integer pontuacao,
                Float pesoPontuacao,
                Boolean aprovadoDp,
                Boolean aprovadoGestor,
                Boolean aprovadoRener,
                Float prvFinal,
                Float totalVariavel,
                Float comissao,
                Float ajusteSalarial,
                String atualizadoPor
        ) {

    public VariaveisColaboradoresDTO(VariaveisColaboradores variaveisColaboradores){
        this(variaveisColaboradores.getId(), variaveisColaboradores.getCpf(),
                variaveisColaboradores.getMes(), variaveisColaboradores.getAno(),
                variaveisColaboradores.getPrv(), variaveisColaboradores.getChat(),
                variaveisColaboradores.getPontuacao(), variaveisColaboradores.getPesoPontuacao(),
                variaveisColaboradores.getAprovadoDp(), variaveisColaboradores.getAprovadoGestor(), variaveisColaboradores.getAprovadoRener(), variaveisColaboradores.getPrvFinal(),
                variaveisColaboradores.getTotalVariavel(), variaveisColaboradores.getComissao(),
                variaveisColaboradores.getAjusteSalarial(), variaveisColaboradores.getAtualizadoPor()
        );
    }
}
