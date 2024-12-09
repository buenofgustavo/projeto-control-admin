package com.centralti.tdm.services.servicesimpl;

import com.centralti.tdm.domain.usuarios.entidades.*;
import com.centralti.tdm.domain.usuarios.repositories.*;
import com.centralti.tdm.services.servicesinterface.ExportService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;


@Service
public class ExportServiceImpl implements ExportService {

    @Autowired
    private GestaoAtivosRepository gestaoAtivosRepository;

    @Autowired
    private ComputadoresRepository computadoresRepository;

    @Autowired
    private VariaveisColaboradoresRepository variaveisColaboradoresRepository;

    @Autowired
    private DadosColaboradoresRepository dadosColaboradoresRepository;

    @Autowired
    private LancamentoDpRepository lancamentoDpRepository;

    public void exportAtivosToExcel(HttpServletResponse response, String localizacao, String atualizadoPor) throws IOException {
        List<GestaoAtivos> listaEntidades;

        if (!"todos".equals(localizacao) && !"todos".equals(atualizadoPor)) {
            listaEntidades = gestaoAtivosRepository.findByLocalizacaoAndAtualizadoPor(localizacao, atualizadoPor);
        } else if (!"todos".equals(localizacao)) {
            listaEntidades = gestaoAtivosRepository.findByLocalizacao(localizacao);
        } else if (!"todos".equals(atualizadoPor)) {
            listaEntidades = gestaoAtivosRepository.findByAtualizadoPor(atualizadoPor);
        } else {
            listaEntidades = gestaoAtivosRepository.findAll();
        }

        String nomeArquivo = "rel_" + localizacao + "_" + atualizadoPor;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("relatorio");

        int rowNum = 0;
        Row headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nome");
        headerRow.createCell(2).setCellValue("Tipo");
        headerRow.createCell(3).setCellValue("Status");
        headerRow.createCell(4).setCellValue("Descrição");
        headerRow.createCell(5).setCellValue("Localização");
        headerRow.createCell(6).setCellValue("Serial");
        headerRow.createCell(7).setCellValue("Atualizado Por");

        for (GestaoAtivos entidade : listaEntidades) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(entidade.getId());
            row.createCell(1).setCellValue(entidade.getNome());
            row.createCell(2).setCellValue(entidade.getTipo());
            row.createCell(3).setCellValue(entidade.getStatus());
            row.createCell(4).setCellValue(entidade.getDescricao());
            row.createCell(5).setCellValue(entidade.getLocalizacao());
            row.createCell(6).setCellValue(entidade.getSerial());
            row.createCell(7).setCellValue(entidade.getAtualizadoPor());
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + nomeArquivo + "\"");

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }

    @Override
    public void exportNotebooksToExcel(HttpServletResponse response, String filial) throws IOException {
        List<Object[]> listaEntidades;
        if ("todos".equalsIgnoreCase(filial)) {
            listaEntidades = computadoresRepository.findAllComputadoresWithDetails();
        } else {
            listaEntidades = computadoresRepository.findComputadoresByFilial(filial);
        }

        String nomeArquivo = "rel_" + filial;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("relatorio");

        int rowNum = 0;
        Row headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("nome_computador");
        headerRow.createCell(1).setCellValue("nome_usuario");
        headerRow.createCell(2).setCellValue("endereco_mac");
        headerRow.createCell(3).setCellValue("nome");
        headerRow.createCell(4).setCellValue("filial");
        headerRow.createCell(5).setCellValue("serial");

        for (Object[] entidade : listaEntidades) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue((String) entidade[0]); // nome_computador
            row.createCell(1).setCellValue((String) entidade[1]); // nome_usuario
            row.createCell(2).setCellValue((String) entidade[2]); // endereco_mac
            row.createCell(3).setCellValue((String) entidade[3]); // nome
            row.createCell(4).setCellValue((String) entidade[4]); // filial
            row.createCell(5).setCellValue((String) entidade[5]); // serial
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + nomeArquivo + "\"");

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }

    @Override
    public void exportNotebooksSemVinculoToExcel(HttpServletResponse response) throws IOException {
        List<Computadores> listaEntidades = computadoresRepository.findAllByUserAtualIsNull();

        String nomeArquivo = "relatorio_computadores.xlsx";
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("relatorio");

        int rowNum = 0;
        Row headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("nome_computador");
        headerRow.createCell(1).setCellValue("nome_usuario");
        headerRow.createCell(2).setCellValue("endereco_mac");
        headerRow.createCell(3).setCellValue("marca");
        headerRow.createCell(4).setCellValue("modelo");
        headerRow.createCell(5).setCellValue("processador");
        headerRow.createCell(6).setCellValue("status");
        headerRow.createCell(7).setCellValue("serial");

        for (Computadores entidade : listaEntidades) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(entidade.getNomeComputador()); // nome_computador
            row.createCell(1).setCellValue(entidade.getNomeUsuario()); // nome_usuario
            row.createCell(2).setCellValue(entidade.getEnderecoMac()); // endereco_mac
            row.createCell(3).setCellValue(entidade.getMarca()); // marca
            row.createCell(4).setCellValue(entidade.getModelo()); // marca
            row.createCell(5).setCellValue(entidade.getProcessador()); // marca
            row.createCell(6).setCellValue(entidade.getStatus()); // nome
            row.createCell(7).setCellValue(entidade.getSerial()); // filial
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + nomeArquivo + "\"");

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }

    @Override
    public void exportVariaveisDP(HttpServletResponse response, String mes, Integer ano) throws IOException {

        List<VariaveisColaboradores> listaEntidades = variaveisColaboradoresRepository.findVariaveisColaboradoresByMesAndAno(mes, ano);

        String nomeArquivo = "rel_" + mes + "_" + ano;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("relatorio");
        String aprovadoGestor;

        int rowNum = 0;
        Row headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("ID DP");
        headerRow.createCell(1).setCellValue("CPF");
        headerRow.createCell(2).setCellValue("Nome");
        headerRow.createCell(3).setCellValue("Departamento");
        headerRow.createCell(4).setCellValue("Mês");
        headerRow.createCell(5).setCellValue("Ano");
        headerRow.createCell(6).setCellValue("PRV");
        headerRow.createCell(7).setCellValue("Comissão");
        headerRow.createCell(8).setCellValue("Chat");
        headerRow.createCell(9).setCellValue("Pontuação");
        headerRow.createCell(10).setCellValue("Peso Pontuação");
        headerRow.createCell(11).setCellValue("PRV Final");
        headerRow.createCell(12).setCellValue("Ajuste Salarial");
        headerRow.createCell(13).setCellValue("Total Variável");
        headerRow.createCell(14).setCellValue("Aprovado Gestor");

        for (VariaveisColaboradores entidade : listaEntidades) {
            Row row = sheet.createRow(rowNum++);
            DadosColaboradores dadosColaboradores = dadosColaboradoresRepository.findByCpf(entidade.getCpf());
            row.createCell(0).setCellValue(dadosColaboradores.getDpid());
            row.createCell(1).setCellValue(entidade.getCpf());
            row.createCell(2).setCellValue(dadosColaboradores.getNome());
            row.createCell(3).setCellValue(dadosColaboradores.getDepartamento());
            row.createCell(4).setCellValue(entidade.getMes());
            row.createCell(5).setCellValue(entidade.getAno());
            row.createCell(6).setCellValue(entidade.getPrv());
            row.createCell(7).setCellValue(entidade.getComissao());
            row.createCell(8).setCellValue(entidade.getChat());
            row.createCell(9).setCellValue(entidade.getPontuacao());
            row.createCell(10).setCellValue(entidade.getPesoPontuacao());
            row.createCell(11).setCellValue(entidade.getPrvFinal());
            row.createCell(12).setCellValue(entidade.getAjusteSalarial());
            row.createCell(13).setCellValue(entidade.getTotalVariavel());
            if (Objects.equals(entidade.getAprovadoGestor(), true)) {
                aprovadoGestor = String.valueOf(true);
            } else {
                aprovadoGestor = String.valueOf(false);
            }
            row.createCell(14).setCellValue(aprovadoGestor);
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + nomeArquivo + "\"");

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }

    @Override
    public void exportLancamentosDP(HttpServletResponse response, String mes, Integer ano) throws IOException {
        List<LancamentoDp> listaEntidades = lancamentoDpRepository.findAllByMesAndAno(mes, ano);

        String nomeArquivo = "rel_" + mes + "_" + ano;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("relatorio");
        String aprovadoRener;

        int rowNum = 0;
        Row headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("ID DP");
        headerRow.createCell(1).setCellValue("CPF");
        headerRow.createCell(2).setCellValue("NOME");
        headerRow.createCell(3).setCellValue("DEPARTAMENTO");
        headerRow.createCell(4).setCellValue("CARGO");
        headerRow.createCell(5).setCellValue("MÊS");
        headerRow.createCell(6).setCellValue("ANO");
        headerRow.createCell(7).setCellValue("BASE");
        headerRow.createCell(8).setCellValue("GRATIFICAÇÕES");
        headerRow.createCell(9).setCellValue("DSR GRATIFICAÇÃO");
        headerRow.createCell(10).setCellValue("OUTROS");
        headerRow.createCell(11).setCellValue("AJUDA DE CUSTO");
        headerRow.createCell(12).setCellValue("AUXÍLIO COMBUSTÍVEL");
        headerRow.createCell(13).setCellValue("AUXÍLIO MORADIA");
        headerRow.createCell(14).setCellValue("HORA EXTRA 100%");
        headerRow.createCell(15).setCellValue("HORA EXTRA 50%");
        headerRow.createCell(16).setCellValue("PRÊMIO PERMANENCIA");
        headerRow.createCell(17).setCellValue("PRÊMIO TEMPO DE SERVIÇO");
        headerRow.createCell(18).setCellValue("REFLEXO HORA EXTRA");
        headerRow.createCell(19).setCellValue("SALÁRIO FAMÍLIA");
        headerRow.createCell(20).setCellValue("ADICIONAL NOTURNO");
        headerRow.createCell(21).setCellValue("DIFERENÇA SALÁRIO");
        headerRow.createCell(22).setCellValue("GRATIFICAÇÃO DE FUNÇÃO");
        headerRow.createCell(23).setCellValue("FÉRIAS");
        headerRow.createCell(24).setCellValue("REEMBOLSO");
        headerRow.createCell(25).setCellValue("OBSERVAÇÃO");
        headerRow.createCell(26).setCellValue("TOTAL PROVENTOS");
        headerRow.createCell(27).setCellValue("APROVADO RENER");


        for (LancamentoDp entidade : listaEntidades) {
            DadosColaboradores dadosColaboradores = dadosColaboradoresRepository.findByCpf(entidade.getCpf());

            if (dadosColaboradores != null){
                Row row = sheet.createRow(rowNum++);

                Float somaTotal = 0f;

                if (dadosColaboradores.getDpid() != null) {
                    row.createCell(0).setCellValue(dadosColaboradores.getDpid());
                } else {
                    row.createCell(0).setCellValue("");
                }
                row.createCell(1).setCellValue(entidade.getCpf());
                row.createCell(2).setCellValue(dadosColaboradores.getNome());
                row.createCell(3).setCellValue(dadosColaboradores.getDepartamento());
                row.createCell(4).setCellValue(dadosColaboradores.getCargo());
                row.createCell(5).setCellValue(entidade.getMes());
                row.createCell(6).setCellValue(entidade.getAno());
                somaTotal += entidade.getBase();
                row.createCell(7).setCellValue(entidade.getBase());
                somaTotal += entidade.getGratificacoes();
                row.createCell(8).setCellValue(entidade.getGratificacoes());
                somaTotal += entidade.getDsrGratificacao();
                row.createCell(9).setCellValue(entidade.getDsrGratificacao());
                somaTotal += entidade.getOutros();
                row.createCell(10).setCellValue(entidade.getOutros());
                somaTotal += entidade.getAjudaCusto();
                row.createCell(11).setCellValue(entidade.getAjudaCusto());
                somaTotal += entidade.getAuxilioCombustivel();
                row.createCell(12).setCellValue(entidade.getAuxilioCombustivel());
                somaTotal += entidade.getAuxilioMoradia();
                row.createCell(13).setCellValue(entidade.getAuxilioMoradia());
                somaTotal += entidade.getHoraExtra100();
                row.createCell(14).setCellValue(entidade.getHoraExtra100());
                somaTotal += entidade.getHoraExtra50();
                row.createCell(15).setCellValue(entidade.getHoraExtra50());
                somaTotal += entidade.getPremioPermanencia();
                row.createCell(16).setCellValue(entidade.getPremioPermanencia());
                somaTotal += entidade.getPremioTempoServico();
                row.createCell(17).setCellValue(entidade.getPremioTempoServico());
                somaTotal += entidade.getReflexoHoraExtra();
                row.createCell(18).setCellValue(entidade.getReflexoHoraExtra());
                somaTotal += entidade.getSalarioFamilia();
                row.createCell(19).setCellValue(entidade.getSalarioFamilia());
                somaTotal += entidade.getAdicionalNoturno();
                row.createCell(20).setCellValue(entidade.getAdicionalNoturno());
                somaTotal += entidade.getDiferencaSalario();
                row.createCell(21).setCellValue(entidade.getDiferencaSalario());
                somaTotal += entidade.getGratificacaoFuncao();
                row.createCell(22).setCellValue(entidade.getGratificacaoFuncao());
                somaTotal += entidade.getFerias();
                row.createCell(23).setCellValue(entidade.getFerias());
                somaTotal += entidade.getReembolso();
                row.createCell(24).setCellValue(entidade.getReembolso());
                row.createCell(25).setCellValue(entidade.getObservacao());
                row.createCell(26).setCellValue(somaTotal);
                if (Objects.equals(entidade.getAprovadoRener(), true)) {
                    aprovadoRener = String.valueOf(true);
                } else {
                    aprovadoRener = String.valueOf(false);
                }
                row.createCell(27).setCellValue(aprovadoRener);
            }

        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + nomeArquivo + "\"");

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }

}
