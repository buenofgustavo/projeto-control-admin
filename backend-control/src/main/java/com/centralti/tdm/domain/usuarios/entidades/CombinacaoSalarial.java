package com.centralti.tdm.domain.usuarios.entidades;

import com.centralti.tdm.domain.usuarios.DTO.CombinacaoSalarialDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name = "combinacao_salarial")
@Entity(name = "combinacao_salarial")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class CombinacaoSalarial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "base")
    private Float base;

    @Column(name = "gratificacao_funcao")
    private Float gratificacaoFuncao;

    @Column(name = "possui_grat_func")
    private Boolean possuiGratFunc;

    @Column(name = "ajuda_custo")
    private Float ajudaCusto;

    @Column(name = "auxilio_combustivel")
    private Float auxilioCombustivel;

    @Column(name = "auxilio_moradia")
    private Float auxilioMoradia;

    @Column(name = "comissao")
    private Boolean comissao;

    @Column(name = "chat")
    private Boolean chat;

    @Column(name = "prv")
    private Float prv;

    @Column(name = "vale_transporte")
    private Boolean valeTransporte;

    @Column(name = "vale_alimentacao")
    private Boolean valeAlimentacao;

    @Column(name = "vale_refeicao")
    private Boolean valeRefeicao;

    @Column(name = "atualizado_por")
    private String atualizadoPor;


    public CombinacaoSalarial(@Valid CombinacaoSalarialDTO combinacaoSalarialDTO) {
        this.id = combinacaoSalarialDTO.id();
        this.cpf = combinacaoSalarialDTO.cpf();
        this.base = combinacaoSalarialDTO.base();
        this.gratificacaoFuncao = combinacaoSalarialDTO.gratificacaoFuncao();
        this.possuiGratFunc = combinacaoSalarialDTO.possuiGratFunc();
        this.ajudaCusto = combinacaoSalarialDTO.ajudaCusto();
        this.auxilioCombustivel = combinacaoSalarialDTO.auxilioCombustivel();
        this.auxilioMoradia = combinacaoSalarialDTO.auxilioMoradia();
        this.comissao = combinacaoSalarialDTO.comissao();
        this.chat = combinacaoSalarialDTO.chat();
        this.prv = combinacaoSalarialDTO.prv();
        this.valeTransporte = combinacaoSalarialDTO.valeTransporte();
        this.valeAlimentacao = combinacaoSalarialDTO.valeAlimentacao();
        this.valeRefeicao = combinacaoSalarialDTO.valeRefeicao();
        this.atualizadoPor = combinacaoSalarialDTO.atualizadoPor();
    }

}
