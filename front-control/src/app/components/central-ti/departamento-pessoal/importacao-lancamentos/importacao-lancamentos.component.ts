import { Component } from '@angular/core';
import { NbToastrService } from '@nebular/theme';
import { ImportacaoLancamentosService } from 'src/app/services/departamento-pessoal/importacao-lancamentos/importacao-lancamentos.service';

@Component({
  selector: 'app-importacao-lancamentos',
  templateUrl: './importacao-lancamentos.component.html',
  styleUrls: ['./importacao-lancamentos.component.scss']
})
export class ImportacaoLancamentosComponent {

  mes: string = "";
  ano: number = 0;

  filesToUpload: FileList | null = null;

  loading: boolean = false

  onFileChange(event: any) {
    this.filesToUpload = event.target.files;
  }

  constructor(private importacaoLancamentosService: ImportacaoLancamentosService,
    private toastrService: NbToastrService) {
  }

  confirmar() {
    this.loading = true
    if (this.mes && this.ano && this.filesToUpload && this.filesToUpload.length > 0) {
      const file: File = this.filesToUpload[0];

      this.importacaoLancamentosService.importacaoLancamentos(this.mes, this.ano, file).subscribe(
        response => {
          this.toastrService.success(`Cadastrado com sucesso!`, "Sucesso", { duration: 5000 });
          setTimeout(() => {
            location.reload(); // Recarrega a página após1 segundos
          }, 2000);
          this.loading = false
        },
        error => {
          if (error.error && error.error.message) {
            this.toastrService.warning(error.error.message, "Erro");
            console.log(error.error.message)
          }
          else {
            this.toastrService.warning('Erro ao cadastrar.', "Erro");
          }
        }
      )
    }
  }

}
