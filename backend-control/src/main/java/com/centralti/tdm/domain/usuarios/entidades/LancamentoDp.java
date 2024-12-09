package com.centralti.tdm.domain.usuarios.entidades;

import com.centralti.tdm.domain.usuarios.DTO.DadosColaboradoresDTO;
import com.centralti.tdm.domain.usuarios.DTO.GestaoAtivosDTO;
import com.centralti.tdm.domain.usuarios.DTO.LancamentoDpDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name = "lancamento_dp")
@Entity(name = "lancamento_dp")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class LancamentoDp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "mes")
    private String mes;

    @Column(name = "ano")
    private Integer ano;

    @Column(name = "base")
    private Float base;

    @Column(name = "gratificacoes")
    private Float gratificacoes;

    @Column(name = "ferias")
    private Float ferias;

    @Column(name = "outros")
    private Float outros;

    @Column(name = "ajuda_custo")
    private Float ajudaCusto;

    @Column(name = "auxilio_combustivel")
    private Float auxilioCombustivel;

    @Column(name = "auxilio_moradia")
    private Float auxilioMoradia;

    @Column(name = "hora_extra100")
    private Float horaExtra100;

    @Column(name = "hora_extra50")
    private Float horaExtra50;

    @Column(name = "premio_permanencia")
    private Float premioPermanencia;

    @Column(name = "premio_tempo_servico")
    private Float premioTempoServico;

    @Column(name = "reflexo_hora_extra")
    private Float reflexoHoraExtra;

    @Column(name = "salario_familia")
    private Float salarioFamilia;

    @Column(name = "adicional_noturno")
    private Float adicionalNoturno;

    @Column(name = "diferenca_salario")
    private Float diferencaSalario;

    @Column(name = "gratificacao_funcao")
    private Float gratificacaoFuncao;

    @Column(name = "dsr_gratificacao")
    private Float dsrGratificacao;

    @Column(name = "reembolso")
    private Float reembolso;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "aprovado_rener")
    private Boolean aprovadoRener;



    public LancamentoDp(@Valid LancamentoDpDTO lancamentoDpDTO) {
        this.cpf = lancamentoDpDTO.cpf();
        this.base = lancamentoDpDTO.base();
        this.gratificacoes = lancamentoDpDTO.gratificacoes();
        this.ferias = lancamentoDpDTO.ferias();
        this.outros = lancamentoDpDTO.outros();
        this.ajudaCusto = lancamentoDpDTO.ajudaCusto();
        this.auxilioCombustivel = lancamentoDpDTO.auxilioCombustivel();
        this.auxilioMoradia = lancamentoDpDTO.auxilioMoradia();
        this.horaExtra100 = lancamentoDpDTO.horaExtra100();
        this.horaExtra50 = lancamentoDpDTO.horaExtra50();
        this.premioPermanencia = lancamentoDpDTO.premioPermanencia();
        this.premioTempoServico = lancamentoDpDTO.premioTempoServico();
        this.reflexoHoraExtra = lancamentoDpDTO.reflexoHoraExtra();
        this.adicionalNoturno = lancamentoDpDTO.adicionalNoturno();
        this.salarioFamilia = lancamentoDpDTO.salarioFamilia();
        this.diferencaSalario = lancamentoDpDTO.diferencaSalario();
        this.gratificacaoFuncao = lancamentoDpDTO.gratificacaoFuncao();
        this.dsrGratificacao = lancamentoDpDTO.dsrGratificacao();
        this.reembolso = lancamentoDpDTO.reembolso();
        this.observacao = lancamentoDpDTO.observacao();
        this.aprovadoRener = lancamentoDpDTO.aprovadoRener();
    }

}
