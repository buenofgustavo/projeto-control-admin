package com.centralti.tdm.domain.usuarios.DTO;

import java.util.ArrayList;
import java.util.List;

public class ColaboradorEventosDTO {
    private String cpf;
    private List<EventoProventoDTO> eventos = new ArrayList<>();

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<EventoProventoDTO> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoProventoDTO> eventos) {
        this.eventos = eventos;
    }
}
