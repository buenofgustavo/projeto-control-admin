package com.centralti.tdm.services.servicesimpl;

import com.centralti.tdm.domain.usuarios.DTO.ColaboradorEventosDTO;
import com.centralti.tdm.domain.usuarios.DTO.EventoProventoDTO;
import com.centralti.tdm.domain.usuarios.DTO.LancamentoDpDTO;
import com.centralti.tdm.domain.usuarios.entidades.DadosColaboradores;
import com.centralti.tdm.domain.usuarios.entidades.LancamentoDp;
import com.centralti.tdm.domain.usuarios.entidades.VariaveisColaboradores;
import com.centralti.tdm.domain.usuarios.repositories.DadosColaboradoresRepository;
import com.centralti.tdm.domain.usuarios.repositories.LancamentoDpRepository;
import com.centralti.tdm.domain.usuarios.repositories.VariaveisColaboradoresRepository;
import com.centralti.tdm.services.servicesinterface.LancamentoDpService;
import com.centralti.tdm.services.servicesinterface.LogColaboradoresService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class LancamentoDpServiceImpl implements LancamentoDpService {

    @Autowired
    LancamentoDpRepository lancamentoDpRepository;

    @Autowired
    VariaveisColaboradoresRepository variaveisColaboradoresRepository;

    @Autowired
    DadosColaboradoresRepository dadosColaboradoresRepository;

    @Autowired
    LogColaboradoresService logColaboradoresService;

    @Override
    public List<ColaboradorEventosDTO> importacaoLancamentosDp(MultipartFile file, String mes, Integer ano) {
        // Usamos um mapa para agrupar os eventos por CPF
        Map<String, ColaboradorEventosDTO> colaboradorEventosMap = new HashMap<>();

        resetarLancamentosExistentes(mes, ano);

        if (file != null && !file.isEmpty()) {
            try (InputStream inputStream = file.getInputStream();
                 Workbook workbook = createWorkbook(inputStream, Objects.requireNonNull(file.getOriginalFilename()))) {

                // Acessa a primeira planilha
                Sheet sheet = workbook.getSheetAt(0);

                // Itera pelas linhas da planilha (pula a primeira linha se for cabeçalho)
                for (Row row : sheet) {
                    // Extrai as colunas necessárias
                    Cell cpfCell = row.getCell(2); // Coluna de CPF
                    Cell codigoEventoCell = row.getCell(6); // Coluna de Código do Evento
                    Cell nomeEventoCell = row.getCell(7); // Coluna de Nome do Evento
                    Cell tipoEventoCell = row.getCell(8); // Coluna de Tipo (PROVENTO, DESCONTO, OUTROS)
                    Cell valorEventoCell = row.getCell(10); // Coluna de Valor

                    // Verifica se o tipo do evento é "PROVENTO"
                    if (tipoEventoCell != null && tipoEventoCell.getCellType() == CellType.STRING
                            && "PROVENTO".equals(tipoEventoCell.getStringCellValue())) {

                        String cpf = cpfCell.getStringCellValue();

                        cpf = cpf.replaceAll("\\D", "");

                        if (cpf.length() == 11) {
                            cpf = cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
                        } else {
                            System.out.println("CPF inválido");
                        }

                        // Se já existe um colaborador com o CPF, adiciona o evento na lista dele
                        ColaboradorEventosDTO colaboradorEventosDTO = colaboradorEventosMap.getOrDefault(cpf, new ColaboradorEventosDTO());
                        colaboradorEventosDTO.setCpf(cpf);

                        // Cria o evento e adiciona à lista de eventos do colaborador
                        EventoProventoDTO eventoProventoDTO = new EventoProventoDTO();
                        eventoProventoDTO.setCodigoEvento((int) codigoEventoCell.getNumericCellValue());
                        eventoProventoDTO.setNomeEvento(nomeEventoCell.getStringCellValue());
                        eventoProventoDTO.setValor(valorEventoCell.getNumericCellValue());

                        colaboradorEventosDTO.getEventos().add(eventoProventoDTO);

                        // Adiciona ou atualiza o colaborador no mapa
                        colaboradorEventosMap.put(cpf, colaboradorEventosDTO);

                        Optional<LancamentoDp> lancamentoDpOptional = lancamentoDpRepository.findLancamentoDpByCpfAndMesAndAno(cpf, mes, ano);

                        LancamentoDp lancamentoDp;
                        if (lancamentoDpOptional.isPresent()) {
                            lancamentoDp = lancamentoDpOptional.get();
                        } else {
                            lancamentoDp = new LancamentoDp();
                            lancamentoDp.setCpf(cpf);
                            lancamentoDp.setMes(mes);
                            lancamentoDp.setAno(ano);
                            lancamentoDp.setGratificacoes(0f);
                            lancamentoDp.setOutros(0f);
                            lancamentoDp.setFerias(0f);
                            lancamentoDp.setAdicionalNoturno(0f);
                            lancamentoDp.setPremioTempoServico(0f);
                            lancamentoDp.setHoraExtra100(0f);
                            lancamentoDp.setDiferencaSalario(0f);
                            lancamentoDp.setAjudaCusto(0f);
                            lancamentoDp.setAuxilioCombustivel(0f);
                            lancamentoDp.setAuxilioMoradia(0f);
                            lancamentoDp.setHoraExtra50(0f);
                            lancamentoDp.setPremioPermanencia(0f);
                            lancamentoDp.setReflexoHoraExtra(0f);
                            lancamentoDp.setSalarioFamilia(0f);
                            lancamentoDp.setBase(0f);
                            lancamentoDp.setGratificacaoFuncao(0f);
                            lancamentoDp.setDsrGratificacao(0f);
                            lancamentoDp.setReembolso(0f);
                        }

                        float gratificacoesAtual = lancamentoDp.getGratificacoes() != null ? lancamentoDp.getGratificacoes() : 0;
                        float outrosAtual = lancamentoDp.getOutros() != null ? lancamentoDp.getOutros() : 0;
                        float feriasAtual = lancamentoDp.getFerias() != null ? lancamentoDp.getFerias() : 0;
                        float adicionalNoturnoAtual = lancamentoDp.getAdicionalNoturno() != null ? lancamentoDp.getAdicionalNoturno() : 0;
                        float premioTempoServicoAtual = lancamentoDp.getPremioTempoServico() != null ? lancamentoDp.getPremioTempoServico() : 0;
                        float horaExtra100Atual = lancamentoDp.getHoraExtra100() != null ? lancamentoDp.getHoraExtra100() : 0;


                        switch (eventoProventoDTO.getCodigoEvento()) {

                            ////////////// BASE //////////////
                            case 8781: // dias Normais
                                lancamentoDp.setBase((float) eventoProventoDTO.getValor());
                                break;
                            case 8797: // dias bolsa auxílio
                                lancamentoDp.setBase((float) eventoProventoDTO.getValor());
                                break;

                            ////////////// GRATIFICAÇÕES //////////////
                            case 20: // gratificação
                                lancamentoDp.setGratificacoes(gratificacoesAtual + (float) eventoProventoDTO.getValor());
                                break;
                            case 286: // honorários
                                lancamentoDp.setGratificacoes(gratificacoesAtual + (float) eventoProventoDTO.getValor());
                                break;
                            case 213: // reflexo dsr honorários
                                lancamentoDp.setGratificacoes(gratificacoesAtual + (float) eventoProventoDTO.getValor());
                                break;

                            case 168: // bonificacao estagiários
                                lancamentoDp.setGratificacoes(gratificacoesAtual + (float) eventoProventoDTO.getValor());
                                break;

                            ////////////// OUTROS //////////////
                            case 225: // auxilio combustivel mes anterior
                                lancamentoDp.setOutros(outrosAtual + (float) eventoProventoDTO.getValor());
                                break;
                            case 9522: // dias afast. p/doenca igual/inf. 15 dias
                                lancamentoDp.setOutros(outrosAtual + (float) eventoProventoDTO.getValor());
                                break;
                            case 8785: // dias afast.inss (p/doenca)
                                lancamentoDp.setOutros(outrosAtual + (float) eventoProventoDTO.getValor());
                                break;
                            case 8786: // dias afast.p/acid.trabalho
                                lancamentoDp.setOutros(outrosAtual + (float) eventoProventoDTO.getValor());
                                break;
                            case 990: // estouro do mes
                                lancamentoDp.setOutros(outrosAtual + (float) eventoProventoDTO.getValor());
                                break;
                            case 836: // inss dif fer desc a maior
                                lancamentoDp.setOutros(outrosAtual + (float) eventoProventoDTO.getValor());
                                break;
                            case 892: // media afast acid trabalho
                                lancamentoDp.setOutros(outrosAtual + (float) eventoProventoDTO.getValor());
                                break;
                            case 9542: // media afast doen dir.int igual/inf 15 dias
                                lancamentoDp.setOutros(outrosAtual + (float) eventoProventoDTO.getValor());
                                break;
                            case 896: // media afast doenca
                                lancamentoDp.setOutros(outrosAtual + (float) eventoProventoDTO.getValor());
                                break;
                            case 894: // media lic. maternidade
                                lancamentoDp.setOutros(outrosAtual + (float) eventoProventoDTO.getValor());
                                break;
                            case 8784: // salario maternidade dias
                                lancamentoDp.setOutros(outrosAtual + (float) eventoProventoDTO.getValor());
                                break;

                            ////////////// FÉRIAS //////////////
                            case 8810: // bolsa auxilio dias de ferias
                                lancamentoDp.setFerias(feriasAtual + (float) eventoProventoDTO.getValor());
                                break;
                            case 8783: // dias ferias
                                lancamentoDp.setFerias(feriasAtual + (float) eventoProventoDTO.getValor());
                                break;
                            case 8192: // diferenca adicional ferias
                                lancamentoDp.setFerias(feriasAtual + (float) eventoProventoDTO.getValor());
                                break;
                            case 8112: // diferenca de 1/3 de ferias
                                lancamentoDp.setFerias(feriasAtual + (float) eventoProventoDTO.getValor());
                                break;
                            case 940: // diferenca de ferias
                                lancamentoDp.setFerias(feriasAtual + (float) eventoProventoDTO.getValor());
                                break;
                            case 8189: // diferenca media hora ferias
                                lancamentoDp.setFerias(feriasAtual + (float) eventoProventoDTO.getValor());
                                break;
                            case 8190: // diferenca media valor ferias
                                lancamentoDp.setFerias(feriasAtual + (float) eventoProventoDTO.getValor());
                                break;
                            case 806: // media horas ferias
                                lancamentoDp.setFerias(feriasAtual + (float) eventoProventoDTO.getValor());
                                break;
                            case 805: // media valor ferias
                                lancamentoDp.setFerias(feriasAtual + (float) eventoProventoDTO.getValor());
                                break;
                            case 807: // vantagens ferias
                                lancamentoDp.setFerias(feriasAtual + (float) eventoProventoDTO.getValor());
                                break;
                            case 931: // vantagens ferias
                                lancamentoDp.setFerias(feriasAtual + (float) eventoProventoDTO.getValor());
                                break;

                            ////////////// ADICIONAL NOTURNO //////////////
                            case 25: // adicional noturno (infor)
                                lancamentoDp.setAdicionalNoturno(adicionalNoturnoAtual + (float) eventoProventoDTO.getValor());
                                break;
                            case 8924: // reflexo horas adic. noturno dsr
                                lancamentoDp.setAdicionalNoturno(adicionalNoturnoAtual + (float) eventoProventoDTO.getValor());
                                break;

                            ////////////// PRÊMIO TEMPO DE SERVIÇO //////////////
                            case 2430: // premio tempo de serviço
                                lancamentoDp.setPremioTempoServico(premioTempoServicoAtual + (float) eventoProventoDTO.getValor());
                                break;
                            case 2431: // premio tempo de serviço sinfar
                                lancamentoDp.setPremioTempoServico(premioTempoServicoAtual + (float) eventoProventoDTO.getValor());
                                break;

                            ////////////// HORA EXTRA 100 //////////////
                            case 210: // horas extras 100%
                                lancamentoDp.setHoraExtra100(horaExtra100Atual + (float) eventoProventoDTO.getValor());
                                break;
                            case 28: // horas extras 100% ma
                                lancamentoDp.setHoraExtra100(horaExtra100Atual + (float) eventoProventoDTO.getValor());

                            ////////////// PROVENTOS COMUNS //////////////
                                break;
                            case 19: // diferença de salários
                                lancamentoDp.setDiferencaSalario((float) eventoProventoDTO.getValor());
                                break;
                            case 264: // ajuda de custo
                                lancamentoDp.setAjudaCusto((float) eventoProventoDTO.getValor());
                                break;
                            case 3110: // auxilio combustível
                                lancamentoDp.setAuxilioCombustivel((float) eventoProventoDTO.getValor());
                                break;
                            case 77: // auxilio moradia
                                lancamentoDp.setAuxilioMoradia((float) eventoProventoDTO.getValor());
                                break;
                            case 209: // horas extras 50%
                                lancamentoDp.setHoraExtra50((float) eventoProventoDTO.getValor());
                                break;
                            case 280: // premio permanencia
                                lancamentoDp.setPremioPermanencia((float) eventoProventoDTO.getValor());
                                break;
                            case 8125: // reflexo horas extras dsr
                                lancamentoDp.setReflexoHoraExtra((float) eventoProventoDTO.getValor());
                                break;
                            case 995: // salario familia
                                lancamentoDp.setSalarioFamilia((float) eventoProventoDTO.getValor());
                                break;
                            case 201: // dsr
                                lancamentoDp.setDsrGratificacao((float) eventoProventoDTO.getValor());
                                break;
                            case 79: // gratificação de função
                                lancamentoDp.setGratificacaoFuncao((float) eventoProventoDTO.getValor());
                                break;
                            case 208: // reembolso
                                lancamentoDp.setReembolso((float) eventoProventoDTO.getValor());
                                break;

                            default:
                                break;
                        }
                        lancamentoDpRepository.save(lancamentoDp); // Save after processing all events for the CPF

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Nenhum arquivo foi enviado.");
        }

        List<ColaboradorEventosDTO> resultList = new ArrayList<>(colaboradorEventosMap.values());

        // Converte o mapa em uma lista de ColaboradorEventosDTO e retorna
        return new ArrayList<>(colaboradorEventosMap.values());
    }

    // Método para criar um Workbook, dependendo da extensão do arquivo
    private Workbook createWorkbook(InputStream inputStream, String fileName) throws IOException {
        if (fileName.endsWith(".xlsx")) {
            return new XSSFWorkbook(inputStream); // Formato OOXML (Excel moderno .xlsx)
        } else if (fileName.endsWith(".xls")) {
            return new HSSFWorkbook(inputStream); // Formato OLE2 (Excel antigo .xls)
        } else {
            throw new IllegalArgumentException("Formato de arquivo não suportado: " + fileName);
        }
    }

    private void resetarLancamentosExistentes(String mes, Integer ano) {
        List<LancamentoDp> lancamentosExistentes = lancamentoDpRepository.findAllByMesAndAno(mes, ano);
        for (LancamentoDp lancamento : lancamentosExistentes) {

            Optional<VariaveisColaboradores> existingVariaveisColaboradoresOpt =
                    variaveisColaboradoresRepository.findVariaveisColaboradoresByCpfAndMesAndAno(
                            lancamento.getCpf(),
                            lancamento.getMes(),
                            lancamento.getAno()
                    );

            if (existingVariaveisColaboradoresOpt.isPresent()) {
                VariaveisColaboradores existingVariaveisColaboradores = existingVariaveisColaboradoresOpt.get();
                existingVariaveisColaboradores.setAprovadoRener(false);
                variaveisColaboradoresRepository.save(existingVariaveisColaboradores);
            }

            lancamento.setGratificacoes(0f);
            lancamento.setOutros(0f);
            lancamento.setFerias(0f);
            lancamento.setAdicionalNoturno(0f);
            lancamento.setPremioTempoServico(0f);
            lancamento.setHoraExtra100(0f);
            lancamento.setDiferencaSalario(0f);
            lancamento.setAjudaCusto(0f);
            lancamento.setAuxilioCombustivel(0f);
            lancamento.setAuxilioMoradia(0f);
            lancamento.setHoraExtra50(0f);
            lancamento.setPremioPermanencia(0f);
            lancamento.setReflexoHoraExtra(0f);
            lancamento.setSalarioFamilia(0f);
            lancamento.setBase(0f);
            lancamento.setGratificacaoFuncao(0f);
            lancamento.setDsrGratificacao(0f);
            lancamento.setReembolso(0f);
            // Você pode adicionar outros campos financeiros aqui, se houver
        }
        lancamentoDpRepository.saveAll(lancamentosExistentes);
    }


    @Override
    public void atualizacaoLancamentosDp(LancamentoDpDTO lancamentoDpDTO) {
        Optional<LancamentoDp> lancamentoDpOptional = lancamentoDpRepository.findLancamentoDpByCpfAndMesAndAno(lancamentoDpDTO.cpf(), lancamentoDpDTO.mes(), lancamentoDpDTO.ano());

        LancamentoDp lancamentoDp;
        if (lancamentoDpOptional.isPresent()) {
            lancamentoDp = lancamentoDpOptional.get();
            lancamentoDp.setObservacao(lancamentoDpDTO.observacao());
            lancamentoDp.setAprovadoRener(lancamentoDpDTO.aprovadoRener());
            lancamentoDpRepository.save(lancamentoDp);
            Optional<VariaveisColaboradores> existingVariaveisColaboradoresOpt =
                    variaveisColaboradoresRepository.findVariaveisColaboradoresByCpfAndMesAndAno(
                            lancamentoDpDTO.cpf(),
                            lancamentoDpDTO.mes(),
                            lancamentoDpDTO.ano()
                    );

            if (existingVariaveisColaboradoresOpt.isPresent()) {
                VariaveisColaboradores existingVariaveisColaboradores = existingVariaveisColaboradoresOpt.get();
                existingVariaveisColaboradores.setAprovadoRener(true);
                String mesAno = existingVariaveisColaboradores.getMes() + "/" + existingVariaveisColaboradores.getAno();
                String mensagem = "Variaveis de " + mesAno + " aprovada pelo Rener ";
                String tipo = "variaveis";
                DadosColaboradores dadosColaboradores = dadosColaboradoresRepository.findByCpf(existingVariaveisColaboradores.getCpf());
                logColaboradoresService.createLogColaboradores(tipo, mensagem, dadosColaboradores.getNome(), dadosColaboradores.getCpf(), mesAno, dadosColaboradores.getDpid());

                variaveisColaboradoresRepository.save(existingVariaveisColaboradores);
            }

        }
    }

}


