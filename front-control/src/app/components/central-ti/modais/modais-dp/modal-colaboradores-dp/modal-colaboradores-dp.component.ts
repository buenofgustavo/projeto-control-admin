import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NbToastrService } from '@nebular/theme';
import { ColaboradorCompleto } from 'src/app/interface/colaboradorCompleto';
import { DocumentosColaboradores } from 'src/app/interface/documentosColaboradores';
import { VariaveisColaboradores } from 'src/app/interface/variaveisColaboradores';
import { CadastroColaboradorService } from 'src/app/services/departamento-pessoal/cadastro-colaborador/cadastro-colaborador.service';
import { VariaveisColaboradoresService } from 'src/app/services/variaveis-colaboradores/variaveis-colaboradores.service';

@Component({
  selector: 'app-modal-colaboradores-dp',
  templateUrl: './modal-colaboradores-dp.component.html',
  styleUrls: ['./modal-colaboradores-dp.component.scss']
})
export class ModalColaboradoresDpComponent {
  
  constructor(private dialogRef: MatDialogRef<ModalColaboradoresDpComponent>,
    private toastrService: NbToastrService,
    private cadastroColaboradorService: CadastroColaboradorService,
    private variaveisColaboradoresService: VariaveisColaboradoresService,
    private router: Router,
    @Inject(MAT_DIALOG_DATA) public data: { colaboradorCompleto: ColaboradorCompleto }) {
      console.log(data)
      console.log(data.colaboradorCompleto.combinacaoSalarialDTO.possuiGratFunc)
  }

  hasDocument: boolean = false;

  arquivos: DocumentosColaboradores = {
    id: null,
    nomeArquivo: '',
    cpf: ''
  }

  cadastrarCombinacao(){
    
    this.data.colaboradorCompleto.combinacaoSalarialDTO.cpf = this.data.colaboradorCompleto.colaboradoresDTO.cpf
    
    console.log(this.data.colaboradorCompleto.combinacaoSalarialDTO)

    this.cadastroColaboradorService.cadastrarCombinacaoSalarial(this.data.colaboradorCompleto.combinacaoSalarialDTO).subscribe(
      response => {
        this.toastrService.success("Cadastrado com sucesso Combinação Salarial", "Sucesso");
        // setTimeout(() => {
        //   this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
        //     this.router.navigate(['/colaboradores-dp']); // Navega para a rota de cadastro de armazém
        //   });
        // }, 1000);
      }
    )
  }

  openImage(): void {   
    this.cadastroColaboradorService.getDocumentos(this.data.colaboradorCompleto.colaboradoresDTO.cpf).subscribe(
      (data2: DocumentosColaboradores | null) => {
        try {
          if (data2) {
            this.arquivos = data2;
            if (this.arquivos) {
              const imageUrl = `../../../../assets/img-uploads/files-documentos/${this.arquivos.cpf}_${this.arquivos.nomeArquivo}`;
              console.log(imageUrl)
              window.open(imageUrl, '_blank');
          } 

          } else {
            this.toastrService.danger('Não possui arquivo.', 'Erro');
            throw new Error('Erro ao abrir arquivo.');
          }
        } catch (error) {
          console.log('Erro ao abrir arquivo.', error);
          this.toastrService.danger('Erro ao abrir arquivo.', 'Erro');
        } 
      },
      error =>{
        if(error.error && error.error.message){
          this.toastrService.warning(error.error.message, "Erro");
  
        }
        else{
          this.toastrService.warning('Erro ao buscar arquivos!', "Erro");
        }
      }
    )
  }

  checkDocumentAvailability(): void {
    this.cadastroColaboradorService.getDocumentos(this.data.colaboradorCompleto.colaboradoresDTO.cpf).subscribe(
      (data2: DocumentosColaboradores | null) => {
        this.hasDocument = !!data2;
      },
      error => {
        this.hasDocument = false;
      }
    );
  }

  ngOnInit(): void {
    this.checkDocumentAvailability(); // Verifica a disponibilidade do documento ao iniciar o componente
  }

}
