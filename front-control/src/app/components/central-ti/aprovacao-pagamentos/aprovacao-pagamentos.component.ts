import { Component, ChangeDetectionStrategy } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NbSidebarService, NbToastrService } from '@nebular/theme';
import { ColaboradorComVariaveis } from 'src/app/interface/colaboradorComVariaveis';
import { Departamentos } from 'src/app/interface/departamento';
import { ColaboradoresService } from 'src/app/services/departamento-pessoal/colaboradores/colaboradores.service';
import { LancamentosDpService } from 'src/app/services/lancamentos-dp/lancamentos-dp.service';
import { DepartamentoFiliaisService } from 'src/app/services/select-departamentos-filiais/departamento-filiais.service';
import { VariaveisColaboradoresService } from 'src/app/services/variaveis-colaboradores/variaveis-colaboradores.service';

@Component({
  selector: 'app-aprovacao-pagamentos',
  templateUrl: './aprovacao-pagamentos.component.html',
  styleUrls: ['./aprovacao-pagamentos.component.scss']
})
export class AprovacaoPagamentosComponent {

  colaboradorComVariaveis: ColaboradorComVariaveis[] = [];
  loading: boolean = false
  mesSelecionado: string = ""
  anoSelecionado: number = 0;
  departamentoSelecionado: string = ""

  prvError: boolean = false;

  constructor(public dialog: MatDialog, private toastrService: NbToastrService,
    private router: Router, private colaboradoresService: ColaboradoresService,
    private variaveisColaboradoresService: VariaveisColaboradoresService,
    private departamentoFiliaisService: DepartamentoFiliaisService,
    private sidebarService: NbSidebarService,
    private lancamentosDpService: LancamentosDpService
  ) {
  }

  getAllColaboradores() {

    this.colaboradoresService.getAllColaboradoresComVariaveisPorDepartamento(this.departamentoSelecionado, this.mesSelecionado, this.anoSelecionado).subscribe(
      (data: ColaboradorComVariaveis[] | null) => {
        if (data && data.length > 0) {
          this.toastrService.success(`Lista atualizada com sucesso!`, "Sucesso", { duration: 5000 });
          data.sort((a, b) => a.colaboradoresDTO.nome.localeCompare(b.colaboradoresDTO.nome));
          this.colaboradorComVariaveis = data.filter(colaborador => colaborador.colaboradoresDTO.status === true && colaborador.colaboradoresDTO.regimeContratacao != 'PJ');
          this.colaboradorComVariaveis.forEach(colaborador => {
            colaborador.diferencas = this.calcularDiferenca(colaborador);
          });
          this.colaboradorComVariaveis.forEach(colaborador => this.calcularTotalProventos(colaborador));

          console.log(this.colaboradorComVariaveis)
          this.loading = false

        } else {
          this.toastrService.warning('A lista de colaboradores é vazia!', 'Aviso');
        }
      },
      (error) => {
        this.toastrService.warning('Erro ao buscar os colaboradores.', 'Erro');
      }
    );
  }

  buscarVariaveis() {
    this.loading = true
    this.getAllColaboradores();

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

  ngOnInit() {
    this.getDepartamentos();
    this.toggle();
  }

  toggle() {
    this.sidebarService.toggle();
  }

  calcularDiferenca(colaborador: any) {
    return {
      diferencaBase: parseFloat(((colaborador.combinacaoSalarialDTO.base || 0) - (colaborador.lancamentoDpDTO.base || 0) - (colaborador.combinacaoSalarialDTO.ajusteSalarial || 0)).toFixed(2)),
      diferencaGratificacao: parseFloat(((colaborador.variaveisColaboradoresDTO.totalVariavel || 0) - (colaborador.lancamentoDpDTO.gratificacoes || 0) - (colaborador.lancamentoDpDTO.dsrGratificacao || 0)).toFixed(2)),
      diferencaAjudaCusto: parseFloat(((colaborador.combinacaoSalarialDTO.ajudaCusto || 0) - (colaborador.lancamentoDpDTO.ajudaCusto || 0)).toFixed(2)),
      diferencaAuxilioCombustivel: parseFloat(((colaborador.combinacaoSalarialDTO.auxilioCombustivel || 0) - (colaborador.lancamentoDpDTO.auxilioCombustivel || 0)).toFixed(2)),
      diferencaAuxilioMoradia: parseFloat(((colaborador.combinacaoSalarialDTO.auxilioMoradia || 0) - (colaborador.lancamentoDpDTO.auxilioMoradia || 0)).toFixed(2)),
      diferencaGratificacaoFuncao: parseFloat(((colaborador.combinacaoSalarialDTO.gratificacaoFuncao || 0) - (colaborador.lancamentoDpDTO.gratificacaoFuncao || 0)).toFixed(2)),
    };
  }

  temDiferenca(colaborador: any): boolean {
    const diferencas = this.calcularDiferenca(colaborador);

    // Verifica se qualquer uma das diferenças é diferente de 0
    return Object.values(diferencas).some(diferenca => diferenca !== 0);
  }

  calcularTotalProventos(colaborador: any): void {
    colaborador.totalProventos =
      colaborador.lancamentoDpDTO.base +
      colaborador.lancamentoDpDTO.gratificacoes +
      colaborador.lancamentoDpDTO.ferias +
      colaborador.lancamentoDpDTO.outros +
      colaborador.lancamentoDpDTO.ajudaCusto +
      colaborador.lancamentoDpDTO.auxilioCombustivel +
      colaborador.lancamentoDpDTO.auxilioMoradia +
      colaborador.lancamentoDpDTO.horaExtra100 +
      colaborador.lancamentoDpDTO.horaExtra50 +
      colaborador.lancamentoDpDTO.premioPermanencia +
      colaborador.lancamentoDpDTO.premioTempoServico +
      colaborador.lancamentoDpDTO.reflexoHoraExtra +
      colaborador.lancamentoDpDTO.salarioFamilia +
      colaborador.lancamentoDpDTO.adicionalNoturno +
      colaborador.lancamentoDpDTO.diferencaSalario +
      colaborador.lancamentoDpDTO.gratificacaoFuncao +
      colaborador.lancamentoDpDTO.dsrGratificacao +
      colaborador.lancamentoDpDTO.reembolso;

    console.log(colaborador.totalProventos)
  }

  aprovar(colaborador: ColaboradorComVariaveis) {
    colaborador.lancamentoDpDTO.aprovadoRener = true
    this.lancamentosDpService.aprovarLancamentos(colaborador.lancamentoDpDTO).subscribe(
      response => {
        this.toastrService.success("Aprovado com sucesso", "Sucesso");
      },
      error => {
        this.toastrService.warning("Erro ao aprovar", "Erro");
      }
    )
  }

  stopAccordion(event: Event): void {
    event.stopPropagation();
  }

}
