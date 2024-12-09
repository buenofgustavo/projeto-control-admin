package com.centralti.tdm.domain.usuarios.DTO;

import com.centralti.tdm.domain.usuarios.entidades.LancamentoDp;

public record LancamentoDpDTO(

         String cpf,
         String mes,
         Integer ano,
         Float base,
         Float gratificacoes,
         Float ferias,
         Float outros,
         Float ajudaCusto,
         Float auxilioCombustivel,
         Float auxilioMoradia,
         Float horaExtra100,
         Float horaExtra50,
         Float premioPermanencia,
         Float premioTempoServico,
         Float reflexoHoraExtra,
         Float salarioFamilia,
         Float adicionalNoturno,
         Float diferencaSalario,
         Float gratificacaoFuncao,
         Float dsrGratificacao,
         Float reembolso,
         String observacao,
         Boolean aprovadoRener

) {

    public LancamentoDpDTO(LancamentoDp lancamentoDp){

        this(
             lancamentoDp.getCpf(), lancamentoDp.getMes(),
             lancamentoDp.getAno(), lancamentoDp.getBase(), lancamentoDp.getGratificacoes(),
             lancamentoDp.getFerias(), lancamentoDp.getOutros(), lancamentoDp.getAjudaCusto(),
             lancamentoDp.getAuxilioCombustivel(), lancamentoDp.getAuxilioMoradia(),
             lancamentoDp.getHoraExtra100(), lancamentoDp.getHoraExtra50(),
             lancamentoDp.getPremioPermanencia(), lancamentoDp.getPremioTempoServico(),
             lancamentoDp.getReflexoHoraExtra(), lancamentoDp.getSalarioFamilia(),
                lancamentoDp.getAdicionalNoturno(), lancamentoDp.getDiferencaSalario(),
                lancamentoDp.getGratificacaoFuncao(), lancamentoDp.getDsrGratificacao(),
                lancamentoDp.getReembolso(), lancamentoDp.getObservacao(),
                lancamentoDp.getAprovadoRener()
        );

    }

}
