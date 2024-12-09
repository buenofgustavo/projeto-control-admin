package com.centralti.tdm.domain.usuarios.entidades;

import com.centralti.tdm.domain.usuarios.DTO.LogColaboradoresDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "log_colaboradores")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogColaboradores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="tipo")
    private String tipo;

    @Column(name="mensagem")
    private String mensagem;

    @Column(name="nome_colaborador")
    private String nomeColaborador;

    @Column(name="cpf_colaborador")
    private String cpfColaborador;

    @Column(name = "usuario")
    private String usuario;

    @Column(name="data_hora")
    private LocalDateTime dataHora;

    @Column(name="mes_ano")
    private String mesAno;

    @Column(name="dpid")
    private Integer dpid;

    public LogColaboradores(LogColaboradoresDTO logColaboradoresDTO){
        this.tipo = logColaboradoresDTO.tipo();
        this.mensagem = logColaboradoresDTO.mensagem();
        this.nomeColaborador = logColaboradoresDTO.nomeColaborador();
        this.cpfColaborador = logColaboradoresDTO.cpfColaborador();
        this.usuario = logColaboradoresDTO.usuario();
        this.dataHora = logColaboradoresDTO.dataHora();
        this.mesAno = logColaboradoresDTO.mesAno();
        this.dpid = logColaboradoresDTO.dpid();
    }

}

