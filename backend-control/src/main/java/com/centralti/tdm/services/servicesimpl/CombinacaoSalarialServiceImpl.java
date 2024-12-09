package com.centralti.tdm.services.servicesimpl;

import com.centralti.tdm.domain.usuarios.DTO.CombinacaoSalarialDTO;
import com.centralti.tdm.domain.usuarios.entidades.CombinacaoSalarial;
import com.centralti.tdm.domain.usuarios.entidades.DadosColaboradores;
import com.centralti.tdm.domain.usuarios.repositories.CombinacaoSalarialRepository;
import com.centralti.tdm.domain.usuarios.repositories.DadosColaboradoresRepository;
import com.centralti.tdm.services.servicesinterface.CombinacaoSalarialService;
import com.centralti.tdm.services.servicesinterface.LogColaboradoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CombinacaoSalarialServiceImpl implements CombinacaoSalarialService {

    @Autowired
    CombinacaoSalarialRepository combinacaoSalarialRepository;

    @Autowired
    LogColaboradoresService logColaboradoresService;

    @Autowired
    DadosColaboradoresRepository dadosColaboradoresRepository;

    @Override
    public void createdCombinacao(CombinacaoSalarialDTO combinacaoSalarialDTO) {

        CombinacaoSalarial existingCombinacaoSalarial = combinacaoSalarialRepository.findByCpf(combinacaoSalarialDTO.cpf());
        DadosColaboradores dadosColaboradores = dadosColaboradoresRepository.findByCpf(combinacaoSalarialDTO.cpf());
        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        String mesAno = "Não é referente a um mês";
        String tipo = "combinacao";

        if (existingCombinacaoSalarial != null) {

            StringBuilder msg = new StringBuilder();

            if (!existingCombinacaoSalarial.getBase().equals(combinacaoSalarialDTO.base())) {
                msg.append("Base alterado de: ").append(existingCombinacaoSalarial.getBase())
                        .append(" para: ").append(combinacaoSalarialDTO.base()).append("\n");
                existingCombinacaoSalarial.setBase(combinacaoSalarialDTO.base());
            }

            if (!existingCombinacaoSalarial.getGratificacaoFuncao().equals(combinacaoSalarialDTO.gratificacaoFuncao())) {
                msg.append("Gratificação Função alterado de: ").append(existingCombinacaoSalarial.getGratificacaoFuncao())
                        .append(" para: ").append(combinacaoSalarialDTO.gratificacaoFuncao()).append("\n");
                existingCombinacaoSalarial.setGratificacaoFuncao(combinacaoSalarialDTO.gratificacaoFuncao());
            }

            if (!existingCombinacaoSalarial.getPossuiGratFunc().equals(combinacaoSalarialDTO.possuiGratFunc())) {
                msg.append("Possui Gratificação Função alterado de: ").append(existingCombinacaoSalarial.getPossuiGratFunc())
                        .append(" para: ").append(combinacaoSalarialDTO.possuiGratFunc()).append("\n");
                existingCombinacaoSalarial.setPossuiGratFunc(combinacaoSalarialDTO.possuiGratFunc());
            }
            if (!existingCombinacaoSalarial.getAjudaCusto().equals(combinacaoSalarialDTO.ajudaCusto())) {
                msg.append("Ajuda Custo alterado de: ").append(existingCombinacaoSalarial.getAjudaCusto())
                        .append(" para: ").append(combinacaoSalarialDTO.ajudaCusto()).append("\n");
                existingCombinacaoSalarial.setAjudaCusto(combinacaoSalarialDTO.ajudaCusto());
            }

            if (!existingCombinacaoSalarial.getAuxilioCombustivel().equals(combinacaoSalarialDTO.auxilioCombustivel())) {
                msg.append("Auxílio Combustível alterado de: ").append(existingCombinacaoSalarial.getAuxilioCombustivel())
                        .append(" para: ").append(combinacaoSalarialDTO.auxilioCombustivel()).append("\n");
                existingCombinacaoSalarial.setAuxilioCombustivel(combinacaoSalarialDTO.auxilioCombustivel());
            }

            if (!existingCombinacaoSalarial.getAuxilioMoradia().equals(combinacaoSalarialDTO.auxilioMoradia())) {
                msg.append("Auxílio Moradia alterado de: ").append(existingCombinacaoSalarial.getAuxilioMoradia())
                        .append(" para: ").append(combinacaoSalarialDTO.auxilioMoradia()).append("\n");
                existingCombinacaoSalarial.setAuxilioMoradia(combinacaoSalarialDTO.auxilioMoradia());
            }

            if (!existingCombinacaoSalarial.getComissao().equals(combinacaoSalarialDTO.comissao())) {
                msg.append("Comissão alterado de: ").append(existingCombinacaoSalarial.getComissao())
                        .append(" para: ").append(combinacaoSalarialDTO.comissao()).append("\n");
                existingCombinacaoSalarial.setComissao(combinacaoSalarialDTO.comissao());
            }

            if (!existingCombinacaoSalarial.getChat().equals(combinacaoSalarialDTO.chat())) {
                msg.append("Chat alterado de: ").append(existingCombinacaoSalarial.getChat())
                        .append(" para: ").append(combinacaoSalarialDTO.chat()).append("\n");
                existingCombinacaoSalarial.setChat(combinacaoSalarialDTO.chat());
            }

            if (!existingCombinacaoSalarial.getPrv().equals(combinacaoSalarialDTO.prv())) {
                msg.append("PRV alterado de: ").append(existingCombinacaoSalarial.getPrv())
                        .append(" para: ").append(combinacaoSalarialDTO.prv()).append("\n");
                existingCombinacaoSalarial.setPrv(combinacaoSalarialDTO.prv());
            }

            if (!existingCombinacaoSalarial.getValeTransporte().equals(combinacaoSalarialDTO.valeTransporte())) {
                msg.append("Vale Transporte alterado de: ").append(existingCombinacaoSalarial.getValeTransporte())
                        .append(" para: ").append(combinacaoSalarialDTO.valeTransporte()).append("\n");
                existingCombinacaoSalarial.setValeTransporte(combinacaoSalarialDTO.valeTransporte());
            }

            if (!existingCombinacaoSalarial.getValeAlimentacao().equals(combinacaoSalarialDTO.valeAlimentacao())) {
                msg.append("Vale Alimentação alterado de: ").append(existingCombinacaoSalarial.getValeAlimentacao())
                        .append(" para: ").append(combinacaoSalarialDTO.valeAlimentacao()).append("\n");
                existingCombinacaoSalarial.setValeAlimentacao(combinacaoSalarialDTO.valeAlimentacao());
            }

            if (!existingCombinacaoSalarial.getValeRefeicao().equals(combinacaoSalarialDTO.valeRefeicao())) {
                msg.append("Vale Refeição alterado de: ").append(existingCombinacaoSalarial.getValeRefeicao())
                        .append(" para: ").append(combinacaoSalarialDTO.valeRefeicao()).append("\n");
                existingCombinacaoSalarial.setValeRefeicao(combinacaoSalarialDTO.valeRefeicao());
            }

            if (!existingCombinacaoSalarial.getAtualizadoPor().equals(emailUsuario)) {
                msg.append("Atualizado por alterado de: ").append(existingCombinacaoSalarial.getAtualizadoPor())
                        .append(" para: ").append(emailUsuario).append("\n");
                existingCombinacaoSalarial.setAtualizadoPor(emailUsuario);
            }

            if(existingCombinacaoSalarial.getPossuiGratFunc()){
                existingCombinacaoSalarial.setGratificacaoFuncao((float) (existingCombinacaoSalarial.getBase() * 0.4));
            } else {
                existingCombinacaoSalarial.setGratificacaoFuncao(0f);
            }

            combinacaoSalarialRepository.save(existingCombinacaoSalarial);

        } else {
            CombinacaoSalarial combinacaoSalarial = new CombinacaoSalarial(combinacaoSalarialDTO);

            if(combinacaoSalarial.getPossuiGratFunc()){
                combinacaoSalarial.setGratificacaoFuncao((float) (combinacaoSalarial.getBase() * 0.4));
            } else {
                combinacaoSalarial.setGratificacaoFuncao(0f);
            }
            combinacaoSalarial.setAtualizadoPor(emailUsuario);

            String mensagem = "Combinação Salarial cadastrada com: \n" +
                    "Base: " + combinacaoSalarial.getBase() + ", " +
                    "PRV: " + combinacaoSalarial.getPrv()+ ", " +
                    "Ajuda de custo: " + combinacaoSalarial.getAjudaCusto()+ ", " +
                    "Auxílio Combustível: " + combinacaoSalarial.getAuxilioCombustivel()+ ", " +
                    "Auxílio moradia: " + combinacaoSalarial.getAuxilioMoradia()+ ", " +
                    "Gratificação de Função: " + combinacaoSalarial.getGratificacaoFuncao()+ ", " +
                    "Chat: " + combinacaoSalarial.getChat()+ ", " +
                    "Comissão: " + combinacaoSalarial.getComissao()+ ", " +
                    "Vale Alimentação: " + combinacaoSalarial.getValeAlimentacao()+ ", " +
                    "Vale Refeição: " + combinacaoSalarial.getValeRefeicao()+ ", " +
                    "Vale Transporte: " + combinacaoSalarial.getValeTransporte()+ ", ";
            logColaboradoresService.createLogColaboradores(tipo, mensagem, dadosColaboradores.getNome(), dadosColaboradores.getCpf(), mesAno, dadosColaboradores.getDpid());

            combinacaoSalarialRepository.save(combinacaoSalarial);

        }
    }
}
