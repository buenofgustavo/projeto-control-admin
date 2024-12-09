package com.centralti.tdm.services.servicesimpl;

import com.centralti.tdm.domain.usuarios.DTO.*;
import com.centralti.tdm.domain.usuarios.entidades.*;
import com.centralti.tdm.domain.usuarios.repositories.*;
import com.centralti.tdm.services.servicesinterface.ColaboradorComVariaveisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorComVariaveisServiceImpl implements ColaboradorComVariaveisService {

    @Autowired
    DadosColaboradoresRepository dadosColaboradoresRepository;
    @Autowired
    ComputadoresRepository computadoresRepository;
    @Autowired
    AcessosRepository acessosRepository;
    @Autowired
    CombinacaoSalarialRepository combinacaoSalarialRepository;
    @Autowired
    VariaveisColaboradoresRepository variaveisColaboradoresRepository;
    @Autowired
    LancamentoDpRepository lancamentoDpRepository;


    @Override
    public ColaboradorComVariaveisDTO findByColaborador(String cpf, String mes, Integer ano) {

        DadosColaboradores dados = dadosColaboradoresRepository.findByCpf(cpf);
        DadosColaboradoresDTO newDados = new DadosColaboradoresDTO(dados);


        Optional<CombinacaoSalarial> combinacaoSalarial = combinacaoSalarialRepository.findCombinacaoSalarialByCpf(dados.getCpf());
        CombinacaoSalarialDTO newCombinacao;
        newCombinacao = combinacaoSalarial.map(CombinacaoSalarialDTO::new).orElseGet(() -> new CombinacaoSalarialDTO("", "", 0f, 0f, false, 0f, 0f, 0f, false, false, 0f, false, false, false, ""));

        Optional<VariaveisColaboradores> variaveis = variaveisColaboradoresRepository.findVariaveisColaboradoresByCpfAndMesAndAno(dados.getCpf(), mes, ano);
        VariaveisColaboradoresDTO newVariaveis;
        newVariaveis = variaveis.map(VariaveisColaboradoresDTO::new).orElseGet(() -> new VariaveisColaboradoresDTO("", "", "", 0, newCombinacao.prv(), 0f, 0, 0f, false, false, false, 0f, 0f, 0f, 0f, ""));

        Acessos acessos = acessosRepository.findByCpf(cpf).orElse(null);
        AcessosDTO newAcessos = new AcessosDTO(acessos);

        Optional<LancamentoDp> lancamentoDp = lancamentoDpRepository.findLancamentoDpByCpfAndMesAndAno(dados.getCpf(), mes, ano);
        LancamentoDpDTO newLancamento;
        newLancamento = lancamentoDp.map(LancamentoDpDTO::new).orElseGet(() -> new LancamentoDpDTO("", "", 0, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, "",false));


        System.out.println(dados);
        ComputadoresDTO newComputador;
        if (dados.getComputador() != null) {
            Computadores computador = computadoresRepository.findByEnderecoMac(dados.getComputador());
            newComputador = new ComputadoresDTO(computador);
        } else {
            newComputador = new ComputadoresDTO("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
        }
        return new ColaboradorComVariaveisDTO(newDados, newComputador, newAcessos, newCombinacao, newVariaveis, newLancamento);

    }

    @Override
    public List<ColaboradorComVariaveisDTO> findAllColaboradores(String mes, Integer ano) {
        List<ColaboradorComVariaveisDTO> colaboradoresCompletoDTO = new ArrayList<>();

        // Consulta todos os dados dos colaboradores
        List<DadosColaboradores> dadosColaboradoresList = dadosColaboradoresRepository.findAll();

        for (DadosColaboradores dados : dadosColaboradoresList) {

            DadosColaboradoresDTO newDados = new DadosColaboradoresDTO(dados);

            Acessos acessos = acessosRepository.findByCpf(dados.getCpf()).orElse(null);
            AcessosDTO newAcessos = new AcessosDTO(acessos);

            Optional<CombinacaoSalarial> combinacaoSalarial = combinacaoSalarialRepository.findCombinacaoSalarialByCpf(dados.getCpf());
            CombinacaoSalarialDTO newCombinacao;
            newCombinacao = combinacaoSalarial.map(CombinacaoSalarialDTO::new).orElseGet(() -> new CombinacaoSalarialDTO("", "", 0f, 0f, false, 0f, 0f, 0f, false, false, 0f, false, false, false, ""));

            Optional<VariaveisColaboradores> variaveis = variaveisColaboradoresRepository.findVariaveisColaboradoresByCpfAndMesAndAno(dados.getCpf(), mes, ano);
            VariaveisColaboradoresDTO newVariaveis;
            newVariaveis = variaveis.map(VariaveisColaboradoresDTO::new).orElseGet(() -> new VariaveisColaboradoresDTO("", "", "", 0, newCombinacao.prv(), 0f, 0, 0f, false, false, false, 0f, 0f, 0f, 0f, ""));

            Optional<LancamentoDp> lancamentoDp = lancamentoDpRepository.findLancamentoDpByCpfAndMesAndAno(dados.getCpf(), mes, ano);
            LancamentoDpDTO newLancamento;
            newLancamento = lancamentoDp.map(LancamentoDpDTO::new).orElseGet(() -> new LancamentoDpDTO("", "", 0, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, "",false));


            ComputadoresDTO newComputador;
            if (dados.getComputador() != null) {
                Computadores computador = computadoresRepository.findByEnderecoMac(dados.getComputador());
                newComputador = new ComputadoresDTO(computador);
            } else {
                newComputador = new ComputadoresDTO("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
            }

            ColaboradorComVariaveisDTO colaboradorComVariaveisDTO = new ColaboradorComVariaveisDTO(newDados, newComputador, newAcessos, newCombinacao, newVariaveis, newLancamento);
            colaboradoresCompletoDTO.add(colaboradorComVariaveisDTO);
        }

        return colaboradoresCompletoDTO;
    }

    @Override
    public List<ColaboradorComVariaveisDTO> findAllColaboradoresByDepartamento(String departamento, String mes, Integer ano) {

        List<ColaboradorComVariaveisDTO> colaboradoresCompletoDTO = new ArrayList<>();

        // Consulta todos os dados dos colaboradores
        List<DadosColaboradores> dadosColaboradoresList = dadosColaboradoresRepository.findDadosColaboradoresByDepartamento(departamento);

        for (DadosColaboradores dados : dadosColaboradoresList) {

            DadosColaboradoresDTO newDados = new DadosColaboradoresDTO(dados);

            Acessos acessos = acessosRepository.findByCpf(dados.getCpf()).orElse(null);
            AcessosDTO newAcessos = new AcessosDTO(acessos);

            Optional<CombinacaoSalarial> combinacaoSalarial = combinacaoSalarialRepository.findCombinacaoSalarialByCpf(dados.getCpf());
            CombinacaoSalarialDTO newCombinacao;
            newCombinacao = combinacaoSalarial.map(CombinacaoSalarialDTO::new).orElseGet(() -> new CombinacaoSalarialDTO("", "", 0f, 0f, false, 0f, 0f, 0f, false, false, 0f, false, false, false, ""));

            Optional<VariaveisColaboradores> variaveis = variaveisColaboradoresRepository.findVariaveisColaboradoresByCpfAndMesAndAno(dados.getCpf(), mes, ano);
            VariaveisColaboradoresDTO newVariaveis;
            newVariaveis = variaveis.map(VariaveisColaboradoresDTO::new).orElseGet(() -> new VariaveisColaboradoresDTO("", "", "", 0, newCombinacao.prv(), 0f, 0, 0f, false, false, false, 0f, 0f, 0f, 0f, ""));

            Optional<LancamentoDp> lancamentoDp = lancamentoDpRepository.findLancamentoDpByCpfAndMesAndAno(dados.getCpf(), mes, ano);
            LancamentoDpDTO newLancamento;
            newLancamento = lancamentoDp.map(LancamentoDpDTO::new).orElseGet(() -> new LancamentoDpDTO("", "", 0, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, "", false));


            ComputadoresDTO newComputador;
            if (dados.getComputador() != null) {
                Computadores computador = computadoresRepository.findByEnderecoMac(dados.getComputador());
                newComputador = new ComputadoresDTO(computador);
            } else {
                newComputador = new ComputadoresDTO("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
            }

            ColaboradorComVariaveisDTO colaboradorComVariaveisDTO = new ColaboradorComVariaveisDTO(newDados, newComputador, newAcessos, newCombinacao, newVariaveis, newLancamento);
            colaboradoresCompletoDTO.add(colaboradorComVariaveisDTO);
        }

        return colaboradoresCompletoDTO;
    }

}