package com.apicontroladmin.demo.domain.DTO;

import com.apicontroladmin.demo.domain.entidades.Computadores;
import com.apicontroladmin.demo.domain.entidades.ComputadoresUnique;

public record ComputadoresDTO (

        String nomeUsuario,

        String nomeComputador,

        String localizacao,

        Float memoriaRam,

        Float capacidadeArmazenamento,

        String marca,

        String modelo,

        String processador,

        String sistemaOperacional,

        String makroInstalado,

        String versaoMakro,

        String enderecoMac

) {
    public ComputadoresDTO(Computadores computadores){
        this(
                computadores.getNomeUsuario(), computadores.getNomeComputador(),
                computadores.getLocalizacao(), computadores.getMemoriaRam(), computadores.getCapacidadeArmazenamento(),
                computadores.getMarca(), computadores.getModelo(), computadores.getProcessador(), computadores.getSistemaOperacional(),
                computadores.getMakroInstalado(), computadores.getVersaoMakro(), computadores.getEnderecoMac()

        );
    }
}