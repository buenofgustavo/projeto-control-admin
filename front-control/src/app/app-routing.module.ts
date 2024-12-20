import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SolicitarCadastroComponent } from './components/central-ti/departamento-pessoal/gestao-pessoal/tipos-de-solicitacao/solicitar-cadastro/solicitar-cadastro.component';
import { SolicitarDesligamentoComponent } from './components/central-ti/departamento-pessoal/gestao-pessoal/tipos-de-solicitacao/solicitar-desligamento/solicitar-desligamento.component';
import { ColaboradoresDpComponent } from './components/central-ti/departamento-pessoal/colaboradores-dp/colaboradores-dp.component';
import { GestaoPessoalComponent } from './components/central-ti/departamento-pessoal/gestao-pessoal/gestao-pessoal.component';
import { CadastrarFeriasComponent } from './components/central-ti/departamento-pessoal/gestao-pessoal/tipos-de-solicitacao/cadastrar-ferias/cadastrar-ferias.component';
import { MudancaDeCargoComponent } from './components/central-ti/departamento-pessoal/gestao-pessoal/tipos-de-solicitacao/mudanca-de-cargo/mudanca-de-cargo.component';
import { ModalColaboradoresDpComponent } from './components/central-ti/modais/modais-dp/modal-colaboradores-dp/modal-colaboradores-dp.component';
import { ModalCadastroUsuarioDpComponent } from './components/central-ti/modais/modais-dp/modal-cadastro-usuario-dp/modal-cadastro-usuario-dp.component';
import { ModalFeriasDpComponent } from './components/central-ti/modais/modais-dp/modal-ferias-dp/modal-ferias-dp.component';
import { ModalMudancaDeCargoDpComponent } from './components/central-ti/modais/modais-dp/modal-mudanca-de-cargo-dp/modal-mudanca-de-cargo-dp.component';
import { ModalDesligamentoDpComponent } from './components/central-ti/modais/modais-dp/modal-desligamento-dp/modal-desligamento-dp.component';
import { UserInfoComponent } from './components/central-ti/departamento-pessoal/templates/user-info/user-info.component';
import { GestaoPessoalTiComponent } from './components/central-ti/departamento-ti/gestao-pessoal-ti/gestao-pessoal-ti.component';
import { CadastroColaboradorTiComponent } from './components/central-ti/departamento-ti/gestao-pessoal-ti/tipos-de-cadastros-ti/cadastro-colaborador-ti/cadastro-colaborador-ti.component';
import { ChamadosTiComponent } from './components/central-ti/departamento-ti/chamados-ti/chamados-ti.component';
import { ModalColaboradoresTiComponent } from './components/central-ti/modais/modais-ti/modal-colaboradores-ti/modal-colaboradores-ti.component';
import { ModalEditarColaboradoresTiComponent } from './components/central-ti/modais/modais-ti/modal-editar-colaboradores-ti/modal-editar-colaboradores-ti.component';
import { VisualizarChamadosGeralComponent } from './components/central-ti/chamados/visualizar-chamados-geral/visualizar-chamados-geral.component';
import { CriarChamadosGeralComponent } from './components/central-ti/chamados/criar-chamados-geral/criar-chamados-geral.component';
import { ModalVisualizarChamadosGeralComponent } from './components/central-ti/modais/modais-chamados/modal-visualizar-chamados-geral/modal-visualizar-chamados-geral.component';
import { ComputadoresTiComponent } from './components/central-ti/departamento-ti/computadores-ti/computadores-ti.component';
import { ModalComputadoresTiComponent } from './components/central-ti/modais/modais-ti/modal-computadores-ti/modal-computadores-ti.component';
import { DesvincularComputadoresComponent } from './components/central-ti/departamento-ti/gestao-pessoal-ti/tipos-de-cadastros-ti/desvincular-computadores/desvincular-computadores.component';
import { DialogConfirmacaoCadastroComponent } from './components/central-ti/modais/modais-dp/dialog/dialog-confirmacao-cadastro/dialog-confirmacao-cadastro.component';
import { CadastrarUsuarioComponent } from './components/central-ti/cadastrar-usuario/cadastrar-usuario.component';
import { SolicitacoesColaboradoresTiComponent } from './components/central-ti/departamento-ti/solicitacoes-colaboradores-ti/solicitacoes-colaboradores-ti.component';
import { ModalVisualizarAtivosTiComponent } from './components/central-ti/modais/modais-ti/modal-visualizar-ativos-ti/modal-visualizar-ativos-ti.component';
import { GestaoImpressorasComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-impressoras/gestao-impressoras.component';
import { GestaoCpdComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-cpd/gestao-cpd.component';
import { GestaoComputadorComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-computador/gestao-computador.component';
import { GestaoMonitorComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-monitor/gestao-monitor.component';
import { GestaoOutrosComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-outros/gestao-outros.component';
import { LoginComponent } from './components/central-ti/login/login.component';
import { AuthGuard } from './auth/auth.guard';
import { HomeComponent } from './components/home/home.component';
import { SolicitacaoColaboradoresDpComponent } from './components/central-ti/departamento-pessoal/solicitacao-colaboradores-dp/solicitacao-colaboradores-dp.component';
import { ColaboradoresTiComponent } from './components/central-ti/departamento-ti/colaboradores-ti/colaboradores-ti.component';
import { ColaboradoresComprasComponent } from './components/central-ti/departamento-compras/colaboradores-compras/colaboradores-compras.component';
import { ComputadoresComprasComponent } from './components/central-ti/departamento-compras/computadores-compras/computadores-compras.component';
import { GestaoMesasComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-mesas/gestao-mesas.component';
import { GestaoArCondicionadoComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-ar-condicionado/gestao-ar-condicionado.component';
import { GestaoBebedourosComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-bebedouros/gestao-bebedouros.component';
import { GestaoRoteadorComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-roteador/gestao-roteador.component';
import { GestaoTransformadorComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-transformador/gestao-transformador.component';
import { GestaoMicroondasComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-microondas/gestao-microondas.component';
import { GestaoArmariosComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-armarios/gestao-armarios.component';
import { GestaoCadeirasComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-cadeiras/gestao-cadeiras.component';
import { GestaoFrigobarComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-frigobar/gestao-frigobar.component';
import { LogComputadoresComponent } from './components/central-ti/departamento-ti/log-computadores/log-computadores.component';
import { LogComputadoresComprasComponent } from './components/central-ti/departamento-compras/log-computadores-compras/log-computadores-compras.component';
import { RelatoriosComprasComponent } from './components/central-ti/departamento-compras/relatorios-compras/relatorios-compras.component';
import { AprovacaoGestoresComponent } from './components/central-ti/aprovacao-gestores/aprovacao-gestores/aprovacao-gestores.component';
import { ImportacaoLancamentosComponent } from './components/central-ti/departamento-pessoal/importacao-lancamentos/importacao-lancamentos.component';
import { RegistroDiarioComponent } from './components/central-ti/departamento-ti/registro-diario/registro-diario.component';
import { PontuacoesColaboradoresComponent } from './components/central-ti/departamento-pessoal/pontuacoes-colaboradores/pontuacoes-colaboradores.component';
import { LogColaboradoresComponent } from './components/central-ti/departamento-pessoal/log-colaboradores/log-colaboradores.component';
import { RelatoriosDpComponent } from './components/central-ti/departamento-pessoal/relatorios-dp/relatorios-dp.component';
import { AprovacaoPagamentosComponent } from './components/central-ti/aprovacao-pagamentos/aprovacao-pagamentos.component';


const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: '',
        redirectTo: 'login',
        pathMatch: 'prefix'
      },
      {
        path: 'login',
        component: LoginComponent,
      }
    ],
  },
  {
    path: '', component: NavbarComponent,
    canActivate: [AuthGuard],
    children: [
      { path: 'home', component: HomeComponent },
      { path: 'gestao-pessoal', component: GestaoPessoalComponent },
      { path: 'cadastrar-usuario', component: CadastrarUsuarioComponent },
      { path: 'solicitacao-colaboradores-dp', component: SolicitacaoColaboradoresDpComponent },
      { path: 'gestao-pessoal-ti', component: GestaoPessoalTiComponent },
      { path: 'cadastrar-ferias', component: CadastrarFeriasComponent },
      { path: 'mudanca-de-cargo', component: MudancaDeCargoComponent },
      { path: 'solicitar-cadastro', component: SolicitarCadastroComponent },
      { path: 'chamados-ti', component: ChamadosTiComponent },
      { path: 'solicitacoes-colaboradores-ti', component: SolicitacoesColaboradoresTiComponent },
      { path: 'computadores-ti', component: ComputadoresTiComponent },
      { path: 'computadores-compras', component: ComputadoresComprasComponent },
      { path: 'solicitar-desligamento', component: SolicitarDesligamentoComponent },
      { path: 'visualizar-chamados-geral', component: VisualizarChamadosGeralComponent },
      { path: 'criar-chamados-geral', component: CriarChamadosGeralComponent },
      { path: 'colaboradores-dp', component: ColaboradoresDpComponent },
      { path: 'colaboradores-compras', component: ColaboradoresComprasComponent },
      { path: 'colaboradores-ti', component: ColaboradoresTiComponent },
      { path: 'gestao-impressoras-ti', component: GestaoImpressorasComponent },
      { path: 'gestao-cpd-ti', component: GestaoCpdComponent },
      { path: 'gestao-computador-ti', component: GestaoComputadorComponent },
      { path: 'gestao-monitor-ti', component: GestaoMonitorComponent },
      { path: 'gestao-outros-ti', component: GestaoOutrosComponent },
      { path: 'gestao-mesas-ti', component: GestaoMesasComponent },
      { path: 'gestao-cadeiras-ti', component: GestaoCadeirasComponent },
      { path: 'gestao-frigobar-ti', component: GestaoFrigobarComponent },
      { path: 'gestao-ar-condicionado-ti', component: GestaoArCondicionadoComponent },
      { path: 'gestao-armarios-ti', component: GestaoArmariosComponent },
      { path: 'gestao-bebedouros-ti', component: GestaoBebedourosComponent },
      { path: 'gestao-roteadores-ti', component: GestaoRoteadorComponent },
      { path: 'gestao-transformadores-ti', component: GestaoTransformadorComponent },
      { path: 'gestao-microondas-ti', component: GestaoMicroondasComponent },
      { path: 'log-computadores-ti', component: LogComputadoresComponent },
      { path: 'relatorios-compras', component: RelatoriosComprasComponent },
      { path: 'log-computadores-compras', component: LogComputadoresComprasComponent },
      { path: 'cadastrar-coloborador-ti', component: CadastroColaboradorTiComponent },
      { path: 'desvincular-computadores-ti', component: DesvincularComputadoresComponent },
      { path: 'modal-editar-coloboradores-ti', component: ModalEditarColaboradoresTiComponent },
      { path: 'modal-mudanca-de-cargo-dp', component: ModalMudancaDeCargoDpComponent },
      { path: 'modal-desligamento-dp', component: ModalDesligamentoDpComponent },
      { path: 'modal-colaboradores-ti', component: ModalColaboradoresTiComponent },
      { path: 'modal-ferias-dp', component: ModalFeriasDpComponent },
      { path: 'modal-colaboradores-dp', component: ModalColaboradoresDpComponent },
      { path: 'modal-cadastrar-usuario-dp', component: ModalCadastroUsuarioDpComponent },
      { path: 'modal-visualizar-chamados-geral', component: ModalVisualizarChamadosGeralComponent },
      { path: 'modal-computadores-ti', component: ModalComputadoresTiComponent },
      { path: 'modal-visualizar-ativos-ti', component: ModalVisualizarAtivosTiComponent },
      { path: 'template-comentario', component: UserInfoComponent },
      { path: 'dialog-confirmacao-cadastrar-usuario', component: DialogConfirmacaoCadastroComponent },
      { path: 'aprovacao-gestores', component: AprovacaoGestoresComponent },
      { path: 'aprovacao-pagamentos', component: AprovacaoPagamentosComponent },
      { path: 'importacao-lancamentos', component: ImportacaoLancamentosComponent },
      { path: 'registro-diario', component: RegistroDiarioComponent },
      { path: 'pontuacoes-colaboradores', component: PontuacoesColaboradoresComponent },
      { path: 'relatorios-dp', component: RelatoriosDpComponent },
      { path: 'logs-colaboradores', component: LogColaboradoresComponent },

    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
