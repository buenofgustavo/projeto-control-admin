import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { NbToastrService } from '@nebular/theme';
import { Filiais } from 'src/app/interface/filiais';
import { Usuario } from 'src/app/interface/usuario-interface';
import { ExportRelatoriosComprasService } from 'src/app/services/export/export-relatorios-compras.service';
import { ExportRelatoriosDpService } from 'src/app/services/export/export-relatorios-dp.service';
import { DepartamentoFiliaisService } from 'src/app/services/select-departamentos-filiais/departamento-filiais.service';
import { UsuarioService } from 'src/app/services/usuario/usuario.service';

@Component({
  selector: 'app-modal-relatorio-dp',
  templateUrl: './modal-relatorio-dp.component.html',
  styleUrls: ['./modal-relatorio-dp.component.scss']
})
export class ModalRelatorioDpComponent {

  mesSelecionado: string = ""
  anoSelecionado: number = 0;

  dados: any;
  idRelatorio: number = 0;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
    private exportRelatoriosDpService: ExportRelatoriosDpService,
    private toastrService: NbToastrService
  ) {
    this.dados = data.dados;
    if (data && data.relatorios) {
      const id = data.relatorios.id;
      console.log('ID recebido:', id);
      this.idRelatorio = id
    }
  }

  gerarRelatorios() {
    if (this.idRelatorio === 1) {
      this.relatorioVariaveisDp();
    } if (this.idRelatorio === 2) {
      this.relatorioLancamentoDp();
    } else {
      this.toastrService.danger("Relatório não existe", "Erro");
    }
  }

  relatorioVariaveisDp() {
    // Chama o serviço para exportar para Excel
    this.exportRelatoriosDpService.exportVariaveisDp(this.mesSelecionado, this.anoSelecionado).subscribe(
      (data) => {
        // Manipula o arquivo Excel retornado, se necessário
        this.downloadFile(data);
        this.toastrService.success("Relatório gerado com sucesso", "Sucesso");
      },
      (error) => {
        // Trata erros de requisição
        console.error('Erro ao exportar para Excel:', error);
        this.toastrService.danger("Erro ao gerar relatório", "Sucesso");
      }
    );
  }

  relatorioLancamentoDp() {
    // Chama o serviço para exportar para Excel
    this.exportRelatoriosDpService.exportLancamentosDp(this.mesSelecionado, this.anoSelecionado).subscribe(
      (data) => {
        // Manipula o arquivo Excel retornado, se necessário
        this.downloadFile(data);
        this.toastrService.success("Relatório gerado com sucesso", "Sucesso");
      },
      (error) => {
        // Trata erros de requisição
        console.error('Erro ao exportar para Excel:', error);
        this.toastrService.danger("Erro ao gerar relatório", "Sucesso");
      }
    );
  }

  private downloadFile(data: Blob) {
    const blob = new Blob([data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
    const url = window.URL.createObjectURL(blob);

    // Cria um link temporário e faz o download do arquivo
    const a = document.createElement('a');
    document.body.appendChild(a);
    a.style.display = 'none';
    a.href = url;

    const agora = new Date();

    // Formatação da data
    const dia = agora.getDate().toString().padStart(2, '0'); // Dia com dois dígitos
    const mes = (agora.getMonth() + 1).toString().padStart(2, '0'); // Mês com dois dígitos
    const ano = agora.getFullYear().toString().slice(-2); // Ano com dois dígitos

    // Formatação da hora
    const hora = agora.getHours().toString().padStart(2, '0'); // Horas com dois dígitos
    const minuto = agora.getMinutes().toString().padStart(2, '0'); // Minutos com dois dígitos

    // Montando a string no formato desejado
    const dia_hora = `${dia}.${mes}.${ano}-${hora}.${minuto}`;

    a.download = `Relatorio_Control_${dia_hora}.xlsx`; // Nome do arquivo a ser baixado
    a.click();

    // Limpa o URL criado
    window.URL.revokeObjectURL(url);
    document.body.removeChild(a);
  }

}

