package com.centralti.tdm.domain.usuarios.DTO;

import com.centralti.tdm.domain.usuarios.entidades.LancamentoDp;
import jakarta.validation.constraints.NotNull;

public record ColaboradorComVariaveisDTO
        (
                @NotNull DadosColaboradoresDTO colaboradoresDTO,
                ComputadoresDTO computadoresDTO,
                AcessosDTO acessosDTO,
                CombinacaoSalarialDTO combinacaoSalarialDTO,
                VariaveisColaboradoresDTO variaveisColaboradoresDTO,
                LancamentoDpDTO lancamentoDpDTO
        ) {

}
