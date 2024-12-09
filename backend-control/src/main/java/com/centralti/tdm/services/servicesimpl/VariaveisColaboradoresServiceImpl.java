package com.centralti.tdm.services.servicesimpl;

import com.centralti.tdm.domain.usuarios.DTO.VariaveisColaboradoresDTO;
import com.centralti.tdm.domain.usuarios.entidades.DadosColaboradores;
import com.centralti.tdm.domain.usuarios.entidades.VariaveisColaboradores;
import com.centralti.tdm.domain.usuarios.repositories.DadosColaboradoresRepository;
import com.centralti.tdm.domain.usuarios.repositories.VariaveisColaboradoresRepository;
import com.centralti.tdm.services.servicesinterface.LogColaboradoresService;
import com.centralti.tdm.services.servicesinterface.VariaveisColaboradoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VariaveisColaboradoresServiceImpl implements VariaveisColaboradoresService {

    @Autowired
    VariaveisColaboradoresRepository variaveisColaboradoresRepository;

    @Autowired
    DadosColaboradoresRepository dadosColaboradoresRepository;

    @Autowired
    LogColaboradoresService logColaboradoresService;

    @Override
    public void createdVariaveis(VariaveisColaboradoresDTO variaveisColaboradoresDTO) {

        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        DadosColaboradores dadosColaboradores = dadosColaboradoresRepository.findByCpf(variaveisColaboradoresDTO.cpf());

        Optional<VariaveisColaboradores> existingVariaveisColaboradoresOpt =
                variaveisColaboradoresRepository.findVariaveisColaboradoresByCpfAndMesAndAno(
                        variaveisColaboradoresDTO.cpf(),
                        variaveisColaboradoresDTO.mes(),
                        variaveisColaboradoresDTO.ano()
                );

        if (existingVariaveisColaboradoresOpt.isPresent()) {

             VariaveisColaboradores existingVariaveisColaboradores = existingVariaveisColaboradoresOpt.get();

             existingVariaveisColaboradores.setAtualizadoPor(emailUsuario);

             existingVariaveisColaboradores.setPontuacao(variaveisColaboradoresDTO.pontuacao());
             existingVariaveisColaboradores.setPesoPontuacao(variaveisColaboradoresDTO.pesoPontuacao());

             String mesAno = existingVariaveisColaboradores.getMes() + "/" + existingVariaveisColaboradores.getAno();
             String tipo = "variaveis";


            if(!existingVariaveisColaboradores.getAprovadoDp()){
                 String msg = "Variavel de " + mesAno + "aprovada pelo DP por: " + emailUsuario;
                 logColaboradoresService.createLogColaboradores(tipo, msg, dadosColaboradores.getNome(), dadosColaboradores.getCpf(), mesAno, dadosColaboradores.getDpid());
             }

             existingVariaveisColaboradores.setAprovadoDp(variaveisColaboradoresDTO.aprovadoDp());


             String mensagem = "Variaveis de " + mesAno + " atualizadas com: \n" +
                    "Pontuação: " + existingVariaveisColaboradores.getPontuacao() + ", " +
                    "Peso: " + existingVariaveisColaboradores.getPesoPontuacao();
            logColaboradoresService.createLogColaboradores(tipo, mensagem, dadosColaboradores.getNome(), dadosColaboradores.getCpf(), mesAno, dadosColaboradores.getDpid());

             variaveisColaboradoresRepository.save(existingVariaveisColaboradores);
        } else {
             VariaveisColaboradores variaveisColaboradores = new VariaveisColaboradores(variaveisColaboradoresDTO);
             variaveisColaboradores.setAtualizadoPor(emailUsuario);

            String mesAno = variaveisColaboradores.getMes() + "/" + variaveisColaboradores.getAno();
            String tipo = "variaveis";
            String mensagem = "Variaveis de " + mesAno + " criadas pelo DP com: \n" +
                    "Pontuação: " + variaveisColaboradores.getPontuacao() + ", " +
                    "Peso: " + variaveisColaboradores.getPesoPontuacao();
            logColaboradoresService.createLogColaboradores(tipo, mensagem, dadosColaboradores.getNome(), dadosColaboradores.getCpf(), mesAno, dadosColaboradores.getDpid());

            variaveisColaboradoresRepository.save(variaveisColaboradores);
        }
    }

    @Override
    public VariaveisColaboradoresDTO FindVariavel(String cpf, String mes, Integer ano) {
        Optional<VariaveisColaboradores> variaveisOpt = variaveisColaboradoresRepository.findVariaveisColaboradoresByCpfAndMesAndAno(cpf, mes, ano);

        // Usando orElseGet para retornar uma instância vazia caso não haja dados
        return variaveisOpt.map(VariaveisColaboradoresDTO::new)
                .orElseGet(() -> new VariaveisColaboradoresDTO("", "", "", 0, 0f, 0f, 0, 0f, false, false, false, 0f, 0f, 0f, 0f, ""));
    }

    @Override
    public void updateVariaveis(VariaveisColaboradoresDTO variaveisColaboradoresDTO) {

        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        DadosColaboradores dadosColaboradores = dadosColaboradoresRepository.findByCpf(variaveisColaboradoresDTO.cpf());

        Optional<VariaveisColaboradores> existingVariaveisColaboradoresOpt =
                variaveisColaboradoresRepository.findVariaveisColaboradoresByCpfAndMesAndAno(
                        variaveisColaboradoresDTO.cpf(),
                        variaveisColaboradoresDTO.mes(),
                        variaveisColaboradoresDTO.ano()
                );

        if (existingVariaveisColaboradoresOpt.isPresent()) {


            VariaveisColaboradores existingVariaveisColaboradores = existingVariaveisColaboradoresOpt.get();

            String mesAno = existingVariaveisColaboradores.getMes() + "/" + existingVariaveisColaboradores.getAno();
            StringBuilder mensagem = new StringBuilder("Variaveis de " + mesAno + " atualizadas: \n");

            existingVariaveisColaboradores.setAtualizadoPor(emailUsuario);

            String tipo = "variaveis";

            if(existingVariaveisColaboradores.getAprovadoGestor() && !existingVariaveisColaboradores.getPontuacao().equals(variaveisColaboradoresDTO.pontuacao())){
                mensagem.append("Pontuação alterada de ").append(existingVariaveisColaboradores.getPontuacao()).append(" para ").append(variaveisColaboradoresDTO.pontuacao()).append(". ");
                existingVariaveisColaboradores.setPontuacao(variaveisColaboradoresDTO.pontuacao());
            }

            if (!existingVariaveisColaboradores.getPrv().equals(variaveisColaboradoresDTO.prv())) {
                mensagem.append("PRV alterado de ").append(existingVariaveisColaboradores.getPrv()).append(" para ").append(variaveisColaboradoresDTO.prv()).append(". ");
                existingVariaveisColaboradores.setPrv(variaveisColaboradoresDTO.prv());
            }

            if (!existingVariaveisColaboradores.getChat().equals(variaveisColaboradoresDTO.chat())) {
                mensagem.append("Chat alterado de ").append(existingVariaveisColaboradores.getChat()).append(" para ").append(variaveisColaboradoresDTO.chat()).append(". ");
                existingVariaveisColaboradores.setChat(variaveisColaboradoresDTO.chat());
            }

            if (!existingVariaveisColaboradores.getPrvFinal().equals(variaveisColaboradoresDTO.prvFinal())) {
                mensagem.append("PRV Final alterado de ").append(existingVariaveisColaboradores.getPrvFinal()).append(" para ").append(variaveisColaboradoresDTO.prvFinal()).append(". ");
                existingVariaveisColaboradores.setPrvFinal(variaveisColaboradoresDTO.prvFinal());
            }

            if (!existingVariaveisColaboradores.getTotalVariavel().equals(variaveisColaboradoresDTO.totalVariavel())) {
                mensagem.append("Total Variável alterado de ").append(existingVariaveisColaboradores.getTotalVariavel()).append(" para ").append(variaveisColaboradoresDTO.totalVariavel()).append(". ");
                existingVariaveisColaboradores.setTotalVariavel(variaveisColaboradoresDTO.totalVariavel());
            }

            if (!existingVariaveisColaboradores.getAprovadoGestor().equals(variaveisColaboradoresDTO.aprovadoGestor())) {
                String msg = "\nVariáveis aprovadas por: " + emailUsuario;
                mensagem.append("Aprovado Gestor alterado de ").append(existingVariaveisColaboradores.getAprovadoGestor()).append(" para ").append(variaveisColaboradoresDTO.aprovadoGestor()).append(". ");
                existingVariaveisColaboradores.setAprovadoGestor(variaveisColaboradoresDTO.aprovadoGestor());
            }

            if (!existingVariaveisColaboradores.getComissao().equals(variaveisColaboradoresDTO.comissao())) {
                mensagem.append("Comissão alterado de ").append(existingVariaveisColaboradores.getComissao()).append(" para ").append(variaveisColaboradoresDTO.comissao()).append(". ");
                existingVariaveisColaboradores.setComissao(variaveisColaboradoresDTO.comissao());
            }

            if (!existingVariaveisColaboradores.getAjusteSalarial().equals(variaveisColaboradoresDTO.ajusteSalarial())) {
                mensagem.append("Ajuste Salarial alterado de ").append(existingVariaveisColaboradores.getAjusteSalarial()).append(" para ").append(variaveisColaboradoresDTO.ajusteSalarial()).append(". ");
                existingVariaveisColaboradores.setAjusteSalarial(variaveisColaboradoresDTO.ajusteSalarial());
            }

            logColaboradoresService.createLogColaboradores(tipo, mensagem.toString(), dadosColaboradores.getNome(), dadosColaboradores.getCpf(), mesAno, dadosColaboradores.getDpid());


            variaveisColaboradoresRepository.save(existingVariaveisColaboradores);
        } else {
            VariaveisColaboradores variaveisColaboradores = new VariaveisColaboradores(variaveisColaboradoresDTO);
            variaveisColaboradores.setAtualizadoPor(emailUsuario);

            String mesAno = variaveisColaboradores.getMes() + "/" + variaveisColaboradores.getAno();
            String tipo = "variaveis";
            String mensagem = "Variaveis de " + mesAno + " criadas pelo gestor com: \n" +
                    "PRV: " + variaveisColaboradores.getPrv() + ", " +
                    "Chat: " + variaveisColaboradores.getChat() + ", " +
                    "Comissão: " + variaveisColaboradores.getComissao();
            logColaboradoresService.createLogColaboradores(tipo, mensagem, dadosColaboradores.getNome(), dadosColaboradores.getCpf(), mesAno, dadosColaboradores.getDpid());


            variaveisColaboradoresRepository.save(variaveisColaboradores);
        }

    }

}
