import { Component, OnInit, ViewChild } from '@angular/core';
import { ColaboradorCompleto } from 'src/app/interface/colaboradorCompleto';
import { NbSidebarService, NbToastrService } from '@nebular/theme';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { VariaveisColaboradores } from 'src/app/interface/variaveisColaboradores';
import { ColaboradoresService } from 'src/app/services/departamento-pessoal/colaboradores/colaboradores.service';
import { VariaveisColaboradoresService } from 'src/app/services/variaveis-colaboradores/variaveis-colaboradores.service';
import { ColaboradorComVariaveis } from 'src/app/interface/colaboradorComVariaveis';
import { DepartamentoFiliaisService } from 'src/app/services/select-departamentos-filiais/departamento-filiais.service';
import { Departamentos } from 'src/app/interface/departamento';

@Component({
  selector: 'app-pontuacoes-colaboradores',
  templateUrl: './pontuacoes-colaboradores.component.html',
  styleUrls: ['./pontuacoes-colaboradores.component.scss']
})
export class PontuacoesColaboradoresComponent {

  colaboradorComVariaveis: ColaboradorComVariaveis[] = [];

  mesSelecionado: string = ""
  anoSelecionado: number = 0;
  departamentoSelecionado: string = ""

  prvError: boolean = false;

  constructor(public dialog: MatDialog, private toastrService: NbToastrService,
    private router: Router, private colaboradoresService: ColaboradoresService,
    private variaveisColaboradoresService: VariaveisColaboradoresService,
    private departamentoFiliaisService: DepartamentoFiliaisService,
    private sidebarService: NbSidebarService,

  ) {
  }

  loading: boolean = true;
  concluido: boolean = false;
  getAllColaboradores() {
    this.loading = true;
    this.colaboradoresService.getAllColaboradoresComVariaveisPorDepartamento(this.departamentoSelecionado, this.mesSelecionado, this.anoSelecionado).subscribe(
      (data: ColaboradorComVariaveis[] | null) => {
        if (data && data.length > 0) {
          this.toastrService.success(`Lista atualizada com sucesso!`, "Sucesso", { duration: 5000 });
          data.sort((a, b) => a.colaboradoresDTO.nome.localeCompare(b.colaboradoresDTO.nome));
          this.colaboradorComVariaveis = data.filter(colaborador => colaborador.colaboradoresDTO.status === true && colaborador.colaboradoresDTO.regimeContratacao != 'PJ');
          console.log(this.colaboradorComVariaveis)
        } else {
          this.toastrService.warning('A lista de colaboradores é vazia!', 'Aviso');
        }
        this.loading = false;
      },
      (error) => {
        this.toastrService.warning('Erro ao buscar os colaboradores.', 'Erro');
        this.loading = false;
      }
    );
  }

  buscarVariaveis() {
    this.getAllColaboradores();
  }

  cadastrarVariaveis(colaborador: ColaboradorComVariaveis) {

    colaborador.variaveisColaboradoresDTO.mes = this.mesSelecionado;
    colaborador.variaveisColaboradoresDTO.ano = this.anoSelecionado;
    colaborador.variaveisColaboradoresDTO.cpf = colaborador.colaboradoresDTO.cpf;
    colaborador.variaveisColaboradoresDTO.aprovadoDp = true
    // Seto aqui o valor do peso
    colaborador.variaveisColaboradoresDTO.pesoPontuacao = 30

    this.variaveisColaboradoresService.cadastrarVariaveisColaboradores(colaborador.variaveisColaboradoresDTO).subscribe(
      response => {
        this.toastrService.success(`Cadastrado com sucesso!`, "Sucesso", { duration: 5000 });
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

  limparVariaveis() {
    this.colaboradorComVariaveis = []
  }

  departamento: Departamentos[] = [];

  getDepartamentos(): void {
    this.departamentoFiliaisService.getAllDepartamentos().subscribe(departamentos => {
      this.departamento = departamentos.sort((a, b) => {
        return a.departamento.localeCompare(b.departamento);
      });
    });
  }

  atualizarVariaveisPosGestor(colaborador: ColaboradorComVariaveis) {

    this.prvError = colaborador.variaveisColaboradoresDTO.prv > colaborador.combinacaoSalarialDTO.prv
    setTimeout(() => {
    }, 2000);

    if (!this.prvError) {
      colaborador.variaveisColaboradoresDTO.aprovadoDp = true
      colaborador.variaveisColaboradoresDTO.aprovadoGestor = false
      this.calculosVariaveis(colaborador);
      this.variaveisColaboradoresService.updateVariaveisColaboradores(colaborador.variaveisColaboradoresDTO).subscribe(
        response => {
          this.toastrService.success(`Necessitará de aprovação do gestor novamente!`, "Sucesso", { duration: 5000 });
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

    } else {
      this.toastrService.warning(`PRV do colaborador ${colaborador.colaboradoresDTO.nome} tem que ser igual ou menor que R$${colaborador.combinacaoSalarialDTO.prv}`, "Erro", { duration: 5000 });
    }


  }

  calculosVariaveis(colaborador: ColaboradorComVariaveis) {
    colaborador.variaveisColaboradoresDTO.prvFinal = Math.max(
      colaborador.variaveisColaboradoresDTO.prv - (colaborador.variaveisColaboradoresDTO.pontuacao * colaborador.variaveisColaboradoresDTO.pesoPontuacao),
      0
    );

    colaborador.variaveisColaboradoresDTO.totalVariavel = Math.max(
      colaborador.variaveisColaboradoresDTO.prv + colaborador.variaveisColaboradoresDTO.comissao + colaborador.variaveisColaboradoresDTO.chat - (colaborador.variaveisColaboradoresDTO.pontuacao * colaborador.variaveisColaboradoresDTO.pesoPontuacao),
      0
    );
  }

  ngOnInit() {
    this.getDepartamentos();
    this.toggle();
  }

  toggle() {
    this.sidebarService.toggle();
  }

}


