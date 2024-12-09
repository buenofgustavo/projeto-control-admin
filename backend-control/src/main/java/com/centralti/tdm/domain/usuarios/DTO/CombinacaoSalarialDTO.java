package com.centralti.tdm.domain.usuarios.DTO;

import com.centralti.tdm.domain.usuarios.entidades.CombinacaoSalarial;

public record CombinacaoSalarialDTO
        (
                String id,
                String cpf,
                Float base,
                Float gratificacaoFuncao,
                Boolean possuiGratFunc,
                Float ajudaCusto,
                Float auxilioCombustivel,
                Float auxilioMoradia,
                Boolean comissao,
                Boolean chat,
                Float prv,
                Boolean valeTransporte,
                Boolean valeAlimentacao,
                Boolean valeRefeicao,
                String atualizadoPor
        ) {

    public CombinacaoSalarialDTO(CombinacaoSalarial combinacaoSalarial){
        this(combinacaoSalarial.getId(), combinacaoSalarial.getCpf(),
                combinacaoSalarial.getBase(), combinacaoSalarial.getGratificacaoFuncao(),
                combinacaoSalarial.getPossuiGratFunc(),
                combinacaoSalarial.getAjudaCusto(), combinacaoSalarial.getAuxilioCombustivel(),
                combinacaoSalarial.getAuxilioMoradia(), combinacaoSalarial.getComissao(),
                combinacaoSalarial.getChat(), combinacaoSalarial.getPrv(),
                combinacaoSalarial.getValeTransporte(),
                combinacaoSalarial.getValeAlimentacao(), combinacaoSalarial.getValeRefeicao(),
                combinacaoSalarial.getAtualizadoPor()
                );
    }
}