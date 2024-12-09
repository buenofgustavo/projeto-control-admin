package com.centralti.tdm.services.servicesimpl;

import com.centralti.tdm.domain.usuarios.DTO.*;
import com.centralti.tdm.domain.usuarios.entidades.Acessos;
import com.centralti.tdm.domain.usuarios.entidades.CombinacaoSalarial;
import com.centralti.tdm.domain.usuarios.entidades.Computadores;
import com.centralti.tdm.domain.usuarios.entidades.DadosColaboradores;
import com.centralti.tdm.domain.usuarios.repositories.AcessosRepository;
import com.centralti.tdm.domain.usuarios.repositories.CombinacaoSalarialRepository;
import com.centralti.tdm.domain.usuarios.repositories.DadosColaboradoresRepository;
import com.centralti.tdm.domain.usuarios.repositories.ComputadoresRepository;
import com.centralti.tdm.services.servicesinterface.ColaboradorCompletoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorCompletoServiceImpl implements ColaboradorCompletoService {

    @Autowired
    DadosColaboradoresRepository dadosColaboradoresRepository;
    @Autowired
    ComputadoresRepository computadoresRepository;
    @Autowired
    AcessosRepository acessosRepository;
    @Autowired
    CombinacaoSalarialRepository combinacaoSalarialRepository;

    @Override
    public ColaboradorCompletoDTO findByColaborador(String cpf) {

        DadosColaboradores dados = dadosColaboradoresRepository.findByCpf(cpf);
        DadosColaboradoresDTO newDados = new DadosColaboradoresDTO(dados);

        Acessos acessos = acessosRepository.findByCpf(cpf).orElse(null);
        AcessosDTO newAcessos = new AcessosDTO(acessos);

        Optional<CombinacaoSalarial> variaveis = combinacaoSalarialRepository.findCombinacaoSalarialByCpf(dados.getCpf());
        CombinacaoSalarialDTO newCombinacao;

        newCombinacao = variaveis.map(CombinacaoSalarialDTO::new).orElseGet(() -> new CombinacaoSalarialDTO("", "", 0f, 0f, false, 0f, 0f, 0f, false, false, 0f, false, false, false, ""));

        System.out.println(dados);
        ComputadoresDTO newComputador;
        if (dados.getComputador() != null) {
            Computadores computador = computadoresRepository.findByEnderecoMac(dados.getComputador());
            newComputador = new ComputadoresDTO(computador);
        } else {
            newComputador = new ComputadoresDTO("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
        }
        return new ColaboradorCompletoDTO(newDados, newComputador, newAcessos, newCombinacao);

    }

    @Override
    public List<ColaboradorCompletoDTO> findAllColaboradores() {
        List<ColaboradorCompletoDTO> colaboradoresCompletoDTO = new ArrayList<>();

        // Consulta todos os dados dos colaboradores
        List<DadosColaboradores> dadosColaboradoresList = dadosColaboradoresRepository.findAll();

        for (DadosColaboradores dados : dadosColaboradoresList) {

            Acessos acessos = acessosRepository.findByCpf(dados.getCpf()).orElse(null);

            DadosColaboradoresDTO newDados = new DadosColaboradoresDTO(dados);
            AcessosDTO newAcessos = new AcessosDTO(acessos);

            Optional<CombinacaoSalarial> variaveis = combinacaoSalarialRepository.findCombinacaoSalarialByCpf(dados.getCpf());
            CombinacaoSalarialDTO newCombinacao;
            newCombinacao = variaveis.map(CombinacaoSalarialDTO::new).orElseGet(() -> new CombinacaoSalarialDTO("", "", 0f, 0f, false, 0f, 0f, 0f, false, false, 0f, false, false, false, ""));


            ComputadoresDTO newComputador;
            if (dados.getComputador() != null) {
                Computadores computador = computadoresRepository.findByEnderecoMac(dados.getComputador());
                newComputador = new ComputadoresDTO(computador);
            } else {
                newComputador = new ComputadoresDTO("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
            }

            ColaboradorCompletoDTO colaboradorCompletoDTO = new ColaboradorCompletoDTO(newDados, newComputador, newAcessos, newCombinacao);
            colaboradoresCompletoDTO.add(colaboradorCompletoDTO);
        }

        return colaboradoresCompletoDTO;
    }

}
