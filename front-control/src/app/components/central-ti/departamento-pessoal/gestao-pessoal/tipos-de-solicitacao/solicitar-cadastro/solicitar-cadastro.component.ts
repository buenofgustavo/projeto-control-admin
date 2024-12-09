import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { NbToastrService } from '@nebular/theme';
import { DialogConfirmacaoCadastroComponent } from 'src/app/components/central-ti/modais/modais-dp/dialog/dialog-confirmacao-cadastro/dialog-confirmacao-cadastro.component';
import { MatDialog } from '@angular/material/dialog';
import { DadosColaboradores } from 'src/app/interface/dados-colaboradores';
import { Router } from '@angular/router';
import { CadastroColaboradorService } from 'src/app/services/departamento-pessoal/cadastro-colaborador/cadastro-colaborador.service';
import { ChatSolicitacoesService } from 'src/app/services/departamento-pessoal/chat-solicitacoes/chat-solicitacoes.service';
import { DepartamentoFiliaisService } from 'src/app/services/select-departamentos-filiais/departamento-filiais.service';
import { Filiais } from 'src/app/interface/filiais';
import { Departamentos } from 'src/app/interface/departamento';
import { CombinacaoSalarial } from 'src/app/interface/combinacaoSalarial';



@Component({
  selector: 'app-solicitar-cadastro',
  templateUrl: './solicitar-cadastro.component.html',
  styleUrls: ['./solicitar-cadastro.component.scss']
})
export class SolicitarCadastroComponent {

  cpfMask = [/\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '-', /\d/, /\d/];

  telefoneMask = ['(', /[1-9]/, /\d/, ')', ' ', /\d/, ' ', /\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/];

  departamento: Departamentos[] = [];

  filial: Filiais[] = [];


  inputItemFormControl = new FormControl();
  textareaItemFormControl = new FormControl();

  mensagem: string = '';
  comentarios: string[] = [];



  enviarMensagem() {
    // Adicionar o comentário à lista de comentários
    this.comentarios.push(this.mensagem);

    // Limpar o campo de entrada
    this.mensagem = '';
  }
  imagemSelecionadaTermo: File | null = null
  anexarTermo(event: any) {
    if (event.target.files && event.target.files[0]) {
      this.imagemSelecionadaTermo = event.target.files[0];
    }
  }

  imagemSelecionadaContrato: File | null = null
  anexarContrato(event: any) {
    if (event.target.files && event.target.files[0]) {
      this.imagemSelecionadaContrato = event.target.files[0];
    }
  }

  constructor(private dialog: MatDialog,
    private toastrService: NbToastrService, private router: Router,
    private cadastroColaboradorService: CadastroColaboradorService,
    private departamentoFiliaisService: DepartamentoFiliaisService,
    private chatSolicitacoesService: ChatSolicitacoesService
  ) {

  }

  openConfirmar() {
    this.dialog.open(DialogConfirmacaoCadastroComponent);
  }

  getFormattedDateTime(): string {
    // Gerando uma data e hora aleatórias para teste
    const dataHora = new Date();
    return dataHora.toLocaleString();
  }

  dadosColaboradores: DadosColaboradores = {
    usuario_solicitante: "",
    nome: "",
    numero: "",
    cpf: "",
    filial: "",
    cargo: "",
    departamento: "",
    computador: null,
    atualizado_por: "",
    termo: false,
    regimeContratacao: "",
    status: true,
    dpid: 0
  }

  combinacaoSalarial: CombinacaoSalarial = {
    cpf: "",
    base: 0,
    gratificacaoFuncao: 0,
    possuiGratFunc: false,
    ajudaCusto: 0,
    auxilioCombustivel: 0,
    auxilioMoradia: 0,
    comissao: false,
    chat: false,
    prv: 0,
    valeTransporte: false,
    valeAlimentacao: false,
    valeRefeicao: false,
    atualizado_por: ""
  }

  preventCopyPaste(event: ClipboardEvent): void {
    event.preventDefault();
  }

  filesToUpload: FileList | null = null;

  onFileChange(event: any) {
    this.filesToUpload = event.target.files;
  }

  create() {

    this.cadastroColaboradorService.cadastrarColaborador(this.dadosColaboradores).subscribe(
      response => {

        if (this.filesToUpload === null) {
          this.toastrService.danger("Upload de arquivo não deu certo!", "Sucesso");
          return;
        }
        else {
          this.cadastroColaboradorService.cadastrarDocumentosColaboradores(this.filesToUpload, this.dadosColaboradores.cpf).subscribe(
            response => {
              this.toastrService.success("Upload de arquivo completo", "Sucesso");
            }
          )
        }

        this.combinacaoSalarial.cpf = this.dadosColaboradores.cpf

        if (this.dadosColaboradores.regimeContratacao != 'PJ') {

          this.cadastroColaboradorService.cadastrarCombinacaoSalarial(this.combinacaoSalarial).subscribe(
            response => {
              this.toastrService.success("Cadastrado com sucesso Combinação Salarial", "Sucesso");
            },
            error => {
              this.toastrService.warning("Erro ao cadastrar Combinação Salarial!", "Erro");
            }
          )

        } else {
          this.toastrService.danger("Não foi cadastrado combinação salarial por ser PJ", "Danger");
        }


        this.toastrService.success("Colaborador cadastrado com sucesso!", "Sucesso");
        setTimeout(() => {
          this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
            this.router.navigate(['/gestao-pessoal']); // Navega para a rota de cadastro de armazém
          });
        }, 3000);
      },
      error => {
        if (error.status === 403) {
          setTimeout(() => {
            location.reload(); // Recarrega a página após1 segundos
          }, 4000);
        } else {
          if (error.error && error.error.message) {
            this.toastrService.warning(error.error.message, "Erro");
            this.toastrService.warning("Conferir CPF", "Erro");
          } else {
            this.toastrService.warning('Erro ao cadastrar colaborador!', "Erro");
          }
        }
      }
    )

  }

  isFormEmpty(): boolean {

    if (this.validarCPF(this.dadosColaboradores.cpf)) {
      if (this.filesToUpload?.length === 1) {
        const inputs: (keyof DadosColaboradores)[] = ['nome', 'numero', 'cpf', 'filial', 'cargo', 'departamento', 'regimeContratacao'];
        return inputs.some(field => !this.dadosColaboradores[field]);
      }
      return true
    }
    return true;
  }

  validarCPF(cpf: string) {
    cpf = cpf.replace(/[^\d]+/g, ''); // Remove caracteres não numéricos

    if (cpf.length !== 11 || cpf.match(/^(.)\1*$/)) {
      return false; // Verifica se o CPF tem 11 dígitos e não é uma sequência repetida
    }

    // Calcula o primeiro dígito verificador
    var sum = 0;
    for (var i = 0; i < 9; i++) {
      sum += parseInt(cpf.charAt(i)) * (10 - i);
    }
    var remainder = 11 - (sum % 11);
    var firstDigit = remainder === 10 || remainder === 11 ? 0 : remainder;

    // Verifica o primeiro dígito verificador
    if (parseInt(cpf.charAt(9)) !== firstDigit) {
      return false;
    }

    // Calcula o segundo dígito verificador
    sum = 0;
    for (var i = 0; i < 10; i++) {
      sum += parseInt(cpf.charAt(i)) * (11 - i);
    }
    remainder = 11 - (sum % 11);
    var secondDigit = remainder === 10 || remainder === 11 ? 0 : remainder;

    // Verifica o segundo dígito verificador
    if (parseInt(cpf.charAt(10)) !== secondDigit) {
      return false;
    }

    return true;
  }

  getFiliais(): void {
    this.departamentoFiliaisService.getAllFiliais().subscribe(filiais => {
      this.filial = filiais.sort((a, b) => {
        return a.filiais.localeCompare(b.filiais);
      });
    });
  }

  getDepartamentos(): void {
    this.departamentoFiliaisService.getAllDepartamentos().subscribe(departamentos => {
      this.departamento = departamentos.sort((a, b) => {
        return a.departamento.localeCompare(b.departamento);
      });
    });
  }

  ngOnInit() {
    this.getFiliais();
    this.getDepartamentos();
  }

}
