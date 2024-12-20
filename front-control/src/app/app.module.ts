import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';

import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { NbEvaIconsModule } from '@nebular/eva-icons';
import {MatTableModule} from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { NbAccordionModule, NbCalendarModule, NbContextMenuComponent, NbDatepickerModule, NbToggleModule, NbWindowModule } from '@nebular/theme'; // Importe o NbDatepickerModule
import { MatDialogModule } from '@angular/material/dialog';

import {
  NbButtonModule,
  NbCardModule,
  NbFormFieldModule,
  NbIconModule,
  NbInputModule,
  NbLayoutModule,
  NbMenuModule,
  NbAutocompleteModule,
  NbSelectModule,
  NbSidebarModule,
  NbStepperModule,
  NbThemeModule,
  NbUserModule,
  NbContextMenuModule,
  NbPositionBuilderService,
  NbCheckboxModule,
  NbToastrModule
} from '@nebular/theme';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SolicitarCadastroComponent } from './components/central-ti/departamento-pessoal/gestao-pessoal/tipos-de-solicitacao/solicitar-cadastro/solicitar-cadastro.component';
import { SolicitarDesligamentoComponent } from './components/central-ti/departamento-pessoal/gestao-pessoal/tipos-de-solicitacao/solicitar-desligamento/solicitar-desligamento.component';
import { ColaboradoresTiComponent } from './components/central-ti/departamento-ti/colaboradores-ti/colaboradores-ti.component';
import { ColaboradoresDpComponent } from './components/central-ti/departamento-pessoal/colaboradores-dp/colaboradores-dp.component';
import { GestaoPessoalComponent } from './components/central-ti/departamento-pessoal/gestao-pessoal/gestao-pessoal.component';
import { CadastrarFeriasComponent } from './components/central-ti/departamento-pessoal/gestao-pessoal/tipos-de-solicitacao/cadastrar-ferias/cadastrar-ferias.component';
import { MudancaDeCargoComponent } from './components/central-ti/departamento-pessoal/gestao-pessoal/tipos-de-solicitacao/mudanca-de-cargo/mudanca-de-cargo.component';
import { ModalColaboradoresDpComponent } from './components/central-ti/modais/modais-dp/modal-colaboradores-dp/modal-colaboradores-dp.component';
import { ModalCadastroUsuarioDpComponent } from './components/central-ti/modais/modais-dp/modal-cadastro-usuario-dp/modal-cadastro-usuario-dp.component';
import { ModalFeriasDpComponent } from './components/central-ti/modais/modais-dp/modal-ferias-dp/modal-ferias-dp.component';
import { ModalMudancaDeCargoDpComponent } from './components/central-ti/modais/modais-dp/modal-mudanca-de-cargo-dp/modal-mudanca-de-cargo-dp.component';
import { ModalDesligamentoDpComponent } from './components/central-ti/modais/modais-dp/modal-desligamento-dp/modal-desligamento-dp.component';
import {MatTabsModule} from '@angular/material/tabs';
import { UserInfoComponent } from './components/central-ti/departamento-pessoal/templates/user-info/user-info.component';
import { GestaoPessoalTiComponent } from './components/central-ti/departamento-ti/gestao-pessoal-ti/gestao-pessoal-ti.component';
import { CadastroColaboradorTiComponent } from './components/central-ti/departamento-ti/gestao-pessoal-ti/tipos-de-cadastros-ti/cadastro-colaborador-ti/cadastro-colaborador-ti.component';
import { ChamadosTiComponent } from './components/central-ti/departamento-ti/chamados-ti/chamados-ti.component';
import { ModalColaboradoresTiComponent } from './components/central-ti/modais/modais-ti/modal-colaboradores-ti/modal-colaboradores-ti.component';
import { ModalEditarColaboradoresTiComponent } from './components/central-ti/modais/modais-ti/modal-editar-colaboradores-ti/modal-editar-colaboradores-ti.component';
import { MatButtonModule } from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';
import { VisualizarChamadosGeralComponent } from './components/central-ti/chamados/visualizar-chamados-geral/visualizar-chamados-geral.component';
import { CriarChamadosGeralComponent } from './components/central-ti/chamados/criar-chamados-geral/criar-chamados-geral.component';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import { ModalVisualizarChamadosGeralComponent } from './components/central-ti/modais/modais-chamados/modal-visualizar-chamados-geral/modal-visualizar-chamados-geral.component';
import { ComputadoresTiComponent } from './components/central-ti/departamento-ti/computadores-ti/computadores-ti.component';
import { ModalComputadoresTiComponent } from './components/central-ti/modais/modais-ti/modal-computadores-ti/modal-computadores-ti.component';
import { DesvincularComputadoresComponent } from './components/central-ti/departamento-ti/gestao-pessoal-ti/tipos-de-cadastros-ti/desvincular-computadores/desvincular-computadores.component';
import { DialogConfirmacaoCadastroComponent } from './components/central-ti/modais/modais-dp/dialog/dialog-confirmacao-cadastro/dialog-confirmacao-cadastro.component';
import { DialogConfirmarFeriasComponent } from './components/central-ti/modais/modais-dp/dialog/dialog-confirmar-ferias/dialog-confirmar-ferias.component';
import { DialogConfirmarDesligamentoComponent } from './components/central-ti/modais/modais-dp/dialog/dialog-confirmar-desligamento/dialog-confirmar-desligamento.component';
import { DialogMudancaCargoComponent } from './components/central-ti/modais/modais-dp/dialog/dialog-mudanca-cargo/dialog-mudanca-cargo.component';
import { DialogExclusaoChamadosTiComponent } from './components/central-ti/modais/modais-ti/dialog/dialog-exclusao-chamados-ti/dialog-exclusao-chamados-ti.component';
import { DialogExclusaoColaboradoresTiComponent } from './components/central-ti/modais/modais-ti/dialog/dialog-exclusao-colaboradores-ti/dialog-exclusao-colaboradores-ti.component';
import { DialogExclusaoComputadoresTiComponent } from './components/central-ti/modais/modais-ti/dialog/dialog-exclusao-computadores-ti/dialog-exclusao-computadores-ti.component';
import { CadastrarUsuarioComponent } from './components/central-ti/cadastrar-usuario/cadastrar-usuario.component';
import { SolicitacoesColaboradoresTiComponent } from './components/central-ti/departamento-ti/solicitacoes-colaboradores-ti/solicitacoes-colaboradores-ti.component';
import { ModalVisualizarAtivosTiComponent } from './components/central-ti/modais/modais-ti/modal-visualizar-ativos-ti/modal-visualizar-ativos-ti.component';
import { GestaoImpressorasComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-impressoras/gestao-impressoras.component';
import { GestaoComputadorComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-computador/gestao-computador.component';
import { GestaoMonitorComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-monitor/gestao-monitor.component';
import { GestaoCpdComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-cpd/gestao-cpd.component';
import { GestaoOutrosComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-outros/gestao-outros.component';
import { ModalGestaoImpressorasComponent } from './components/central-ti/modais/modais-ti/gestao-de-ativos/modal-gestao-impressoras/modal-gestao-impressoras.component';
import { ModalGestaoComputadoresComponent } from './components/central-ti/modais/modais-ti/gestao-de-ativos/modal-gestao-computadores/modal-gestao-computadores.component';
import { ModalGestaoCpdComponent } from './components/central-ti/modais/modais-ti/gestao-de-ativos/modal-gestao-cpd/modal-gestao-cpd.component';
import { ModalGestaoMonitoresComponent } from './components/central-ti/modais/modais-ti/gestao-de-ativos/modal-gestao-monitores/modal-gestao-monitores.component';
import { ModalGestaoOutrosComponent } from './components/central-ti/modais/modais-ti/gestao-de-ativos/modal-gestao-outros/modal-gestao-outros.component';
import { LoginComponent } from './components/central-ti/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { ModalVisualizarSolicitacoesComponent } from './components/central-ti/modais/modais-ti/modal-visualizar-solicitacoes/modal-visualizar-solicitacoes.component';
import { SolicitacaoColaboradoresDpComponent } from './components/central-ti/departamento-pessoal/solicitacao-colaboradores-dp/solicitacao-colaboradores-dp.component';
import { ModalEditarAtivosTiComponent } from './components/central-ti/modais/modais-ti/modal-editar-ativos-ti/modal-editar-ativos-ti.component';
import { TextMaskModule } from 'angular2-text-mask';
import { ColaboradoresComprasComponent } from './components/central-ti/departamento-compras/colaboradores-compras/colaboradores-compras.component';
import { ComputadoresComprasComponent } from './components/central-ti/departamento-compras/computadores-compras/computadores-compras.component';
import { ModalLogComputadoresComponent } from './components/central-ti/modais/modais-ti/modal-log-computadores/modal-log-computadores.component';
import { ModalGestaoMesaComponent } from './components/central-ti/modais/modais-ti/gestao-de-ativos/modal-gestao-mesa/modal-gestao-mesa.component';
import { GestaoMesasComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-mesas/gestao-mesas.component';
import { GestaoArCondicionadoComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-ar-condicionado/gestao-ar-condicionado.component';
import { GestaoArmariosComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-armarios/gestao-armarios.component';
import { GestaoBebedourosComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-bebedouros/gestao-bebedouros.component';
import { GestaoMicroondasComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-microondas/gestao-microondas.component';
import { GestaoRoteadorComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-roteador/gestao-roteador.component';
import { GestaoTransformadorComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-transformador/gestao-transformador.component';
import { ModalGestaoArCondicionadoComponent } from './components/central-ti/modais/modais-ti/gestao-de-ativos/modal-gestao-ar-condicionado/modal-gestao-ar-condicionado.component';
import { ModalGestaoArmariosComponent } from './components/central-ti/modais/modais-ti/gestao-de-ativos/modal-gestao-armarios/modal-gestao-armarios.component';
import { ModalGestaoBebedourosComponent } from './components/central-ti/modais/modais-ti/gestao-de-ativos/modal-gestao-bebedouros/modal-gestao-bebedouros.component';
import { ModalGestaoMicroondasComponent } from './components/central-ti/modais/modais-ti/gestao-de-ativos/modal-gestao-microondas/modal-gestao-microondas.component';
import { ModalGestaoRoteadorComponent } from './components/central-ti/modais/modais-ti/gestao-de-ativos/modal-gestao-roteador/modal-gestao-roteador.component';
import { ModalGestaoTransformadorComponent } from './components/central-ti/modais/modais-ti/gestao-de-ativos/modal-gestao-transformador/modal-gestao-transformador.component';
import { GestaoCadeirasComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-cadeiras/gestao-cadeiras.component';
import { ModalGestaoCadeirasComponent } from './components/central-ti/modais/modais-ti/gestao-de-ativos/modal-gestao-cadeiras/modal-gestao-cadeiras.component';
import { GestaoFrigobarComponent } from './components/central-ti/departamento-ti/gestao-de-ativos/gestao-frigobar/gestao-frigobar.component';
import { ModalGestaoFrigobarComponent } from './components/central-ti/modais/modais-ti/gestao-de-ativos/modal-gestao-frigobar/modal-gestao-frigobar.component';
import { LogComputadoresComponent } from './components/central-ti/departamento-ti/log-computadores/log-computadores.component';
import { LogComputadoresComprasComponent } from './components/central-ti/departamento-compras/log-computadores-compras/log-computadores-compras.component';
import { RelatoriosComprasComponent } from './components/central-ti/departamento-compras/relatorios-compras/relatorios-compras.component';
import { ModalRelatoriosComponent } from './components/central-ti/modais/modal-relatorios/modal-relatorios.component';
import { AprovacaoGestoresComponent } from './components/central-ti/aprovacao-gestores/aprovacao-gestores/aprovacao-gestores.component';
import { ImportacaoLancamentosComponent } from './components/central-ti/departamento-pessoal/importacao-lancamentos/importacao-lancamentos.component';
import { RegistroDiarioComponent } from './components/central-ti/departamento-ti/registro-diario/registro-diario.component';
import { ModalRegistroDiarioComponent } from './components/central-ti/departamento-ti/registro-diario/modais/modal-registro-diario/modal-registro-diario.component';
import { PontuacoesColaboradoresComponent } from './components/central-ti/departamento-pessoal/pontuacoes-colaboradores/pontuacoes-colaboradores.component';
import { RelatoriosDpComponent } from './components/central-ti/departamento-pessoal/relatorios-dp/relatorios-dp.component';
import { ModalRelatorioDpComponent } from './components/central-ti/departamento-pessoal/relatorios-dp/modais/modal-relatorio-dp/modal-relatorio-dp.component';
import { LogColaboradoresComponent } from './components/central-ti/departamento-pessoal/log-colaboradores/log-colaboradores.component';
import { AprovacaoPagamentosComponent } from './components/central-ti/aprovacao-pagamentos/aprovacao-pagamentos.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SolicitarCadastroComponent,
    SolicitarDesligamentoComponent,
    ColaboradoresTiComponent,
    ColaboradoresDpComponent,
    GestaoPessoalComponent,
    CadastrarFeriasComponent,
    MudancaDeCargoComponent,
    ModalColaboradoresDpComponent,
    ModalCadastroUsuarioDpComponent,
    ModalFeriasDpComponent,
    ModalMudancaDeCargoDpComponent,
    ModalDesligamentoDpComponent,
    UserInfoComponent,
    GestaoPessoalTiComponent,
    CadastroColaboradorTiComponent,
    ChamadosTiComponent,
    ModalColaboradoresTiComponent,
    ModalEditarColaboradoresTiComponent,
    VisualizarChamadosGeralComponent,
    CriarChamadosGeralComponent,
    ModalVisualizarChamadosGeralComponent,
    ComputadoresTiComponent,
    ModalComputadoresTiComponent,
    DesvincularComputadoresComponent,
    DialogConfirmacaoCadastroComponent,
    DialogConfirmarFeriasComponent,
    DialogConfirmarDesligamentoComponent,
    DialogMudancaCargoComponent,
    DialogExclusaoChamadosTiComponent,
    DialogExclusaoColaboradoresTiComponent,
    DialogExclusaoComputadoresTiComponent,
    CadastrarUsuarioComponent,
    SolicitacoesColaboradoresTiComponent,
    ModalVisualizarAtivosTiComponent,
    GestaoImpressorasComponent,
    GestaoComputadorComponent,
    GestaoMonitorComponent,
    GestaoCpdComponent,
    GestaoOutrosComponent,
    ModalGestaoImpressorasComponent,
    ModalGestaoComputadoresComponent,
    ModalGestaoCpdComponent,
    ModalGestaoMonitoresComponent,
    ModalGestaoOutrosComponent,
    LoginComponent,
    HomeComponent,
    ModalVisualizarSolicitacoesComponent,
    SolicitacaoColaboradoresDpComponent,
    ModalEditarAtivosTiComponent,
    ColaboradoresComprasComponent,
    ComputadoresComprasComponent,
    ModalLogComputadoresComponent,
    ModalGestaoMesaComponent,
    GestaoMesasComponent,
    GestaoArCondicionadoComponent,
    GestaoArmariosComponent,
    GestaoBebedourosComponent,
    GestaoMicroondasComponent,
    GestaoRoteadorComponent,
    GestaoTransformadorComponent,
    ModalGestaoArCondicionadoComponent,
    ModalGestaoArmariosComponent,
    ModalGestaoBebedourosComponent,
    ModalGestaoMicroondasComponent,
    ModalGestaoRoteadorComponent,
    ModalGestaoTransformadorComponent,
    GestaoCadeirasComponent,
    ModalGestaoCadeirasComponent,
    GestaoFrigobarComponent,
    ModalGestaoFrigobarComponent,
    LogComputadoresComponent,
    LogComputadoresComprasComponent,
    RelatoriosComprasComponent,
    ModalRelatoriosComponent,
    AprovacaoGestoresComponent,
    ImportacaoLancamentosComponent,
    RegistroDiarioComponent,
    ModalRegistroDiarioComponent,
    PontuacoesColaboradoresComponent,
    RelatoriosDpComponent,
    ModalRelatorioDpComponent,
    LogColaboradoresComponent,
    AprovacaoPagamentosComponent,
  ],
  imports: [
    AppRoutingModule, HttpClientModule, FormsModule, ReactiveFormsModule, BrowserModule, BrowserAnimationsModule, NbEvaIconsModule,
    NbButtonModule, NbCardModule, NbFormFieldModule, NbIconModule, NbInputModule,NbLayoutModule,NbMenuModule.forRoot(),NbSelectModule,
    NbSidebarModule.forRoot(), NbStepperModule, NbThemeModule.forRoot({ name: 'default' }),NbUserModule,MatTableModule, NbContextMenuModule,
    MatPaginatorModule,NbCheckboxModule,NbDatepickerModule.forRoot(),NbWindowModule.forRoot(),MatTabsModule,MatDialogModule,
    NbContextMenuModule,MatButtonModule,MatMenuModule,MatInputModule,MatFormFieldModule,FormsModule,NbToastrModule.forRoot(),
    MatProgressSpinnerModule, TextMaskModule,NbAutocompleteModule, NbCalendarModule, NbToggleModule, NbAccordionModule 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
