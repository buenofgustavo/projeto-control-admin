package com.centralti.tdm.domain.usuarios.entidades;

import com.centralti.tdm.domain.usuarios.DTO.VariaveisColaboradoresDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name = "variaveis_colaboradores")
@Entity(name = "variaveis_colaboradores")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class VariaveisColaboradores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "mes")
    private String mes;

    @Column(name = "ano")
    private Integer ano;

    @Column(name = "prv")
    private Float prv;

    @Column(name = "chat")
    private Float chat;

    @Column(name = "pontuacao")
    private Integer pontuacao;

    @Column(name = "peso_pontuacao")
    private Float pesoPontuacao;

    @Column(name = "prv_final")
    private Float prvFinal;

    @Column(name = "total_variavel")
    private Float totalVariavel;

    @Column(name = "aprovado_dp")
    private Boolean aprovadoDp;

    @Column(name = "aprovado_gestor")
    private Boolean aprovadoGestor;

    @Column(name = "aprovado_rener")
    private Boolean aprovadoRener;

    @Column(name = "comissao")
    private Float comissao;

    @Column(name = "ajuste_salarial")
    private Float ajusteSalarial;

    @Column(name = "atualizado_por")
    private String atualizadoPor;


    public VariaveisColaboradores(@Valid VariaveisColaboradoresDTO variaveisColaboradoresDTO) {
        this.id = variaveisColaboradoresDTO.id();
        this.cpf = variaveisColaboradoresDTO.cpf();
        this.mes = variaveisColaboradoresDTO.mes();
        this.ano = variaveisColaboradoresDTO.ano();
        this.prv = variaveisColaboradoresDTO.prv();
        this.chat = variaveisColaboradoresDTO.chat();
        this.pontuacao = variaveisColaboradoresDTO.pontuacao();
        this.pesoPontuacao = variaveisColaboradoresDTO.pesoPontuacao();
        this.prvFinal = variaveisColaboradoresDTO.prvFinal();
        this.totalVariavel = variaveisColaboradoresDTO.totalVariavel();
        this.aprovadoDp = variaveisColaboradoresDTO.aprovadoDp();
        this.aprovadoGestor = variaveisColaboradoresDTO.aprovadoGestor();
        this.aprovadoRener = variaveisColaboradoresDTO.aprovadoRener();
        this.comissao = variaveisColaboradoresDTO.comissao();
        this.ajusteSalarial = variaveisColaboradoresDTO.ajusteSalarial();
        this.atualizadoPor = variaveisColaboradoresDTO.atualizadoPor();
    }

}
