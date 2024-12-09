package com.centralti.tdm.domain.usuarios.DTO;

public class EventoProventoDTO {
    private int codigoEvento;
    private String nomeEvento;
    private double valor;

    // Getters e Setters
    public int getCodigoEvento() {
        return codigoEvento;
    }

    public void setCodigoEvento(int codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}