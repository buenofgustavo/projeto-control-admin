import { Component, OnInit, ViewChild } from '@angular/core';
import { Computadores } from 'src/app/interface/computadores';
import { ModalComputadoresTiComponent } from '../../modais/modais-ti/modal-computadores-ti/modal-computadores-ti.component';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ComputadoresService } from 'src/app/services/departamento-ti/computadores/computadores.service';
import { NbSidebarService, NbToastrService } from '@nebular/theme';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ColaboradorCompleto } from 'src/app/interface/colaboradorCompleto';
import { ModalColaboradoresDpComponent } from '../../modais/modais-dp/modal-colaboradores-dp/modal-colaboradores-dp.component';
import { ColaboradoresService } from 'src/app/services/departamento-pessoal/colaboradores/colaboradores.service';
import { ModalColaboradoresTiComponent } from '../../modais/modais-ti/modal-colaboradores-ti/modal-colaboradores-ti.component';
import { VariaveisColaboradores } from 'src/app/interface/variaveisColaboradores';
import { ColaboradorComVariaveis } from 'src/app/interface/colaboradorComVariaveis';
import { VariaveisColaboradoresService } from 'src/app/services/variaveis-colaboradores/variaveis-colaboradores.service';
import { Usuario } from 'src/app/interface/usuario-interface';
import { UsuarioService } from 'src/app/services/usuario/usuario.service';

@Component({
    selector: 'app-aprovacao-gestores',
    templateUrl: './aprovacao-gestores.component.html',
    styleUrls: ['./aprovacao-gestores.component.scss']
})
export class AprovacaoGestoresComponent implements OnInit {

    prvError: boolean = false;

    usuario: Usuario = {
        login: "",
        password: "",
        role: "",
        name: "",
        departamento: ""
    }

    loadUser() {
        this.usuarioService.getUserByEmail().subscribe(
            (user: Usuario) => {
                this.usuario = user;
                console.log(this.usuario.role)
            },
            (error) => {
                console.error('Erro ao carregar dados do Pessoa:', error);
            }
        );
    }

    colaboradorComVariaveis: ColaboradorComVariaveis[] = [];

    mesSelecionado: string = ""
    anoSelecionado: number = 0;

    constructor(public dialog: MatDialog, private toastrService: NbToastrService,
        private router: Router, private colaboradoresService: ColaboradoresService,
        private variaveisColaboradoresService: VariaveisColaboradoresService,
        private usuarioService: UsuarioService,
        private sidebarService: NbSidebarService,
    ) {
        console.log(this.colaboradorComVariaveis)
    }



    loading: boolean = true;
    concluido: boolean = false;
    getAllColaboradores() {
        this.loading = true;
        this.colaboradoresService.getAllColaboradoresComVariaveisPorDepartamento(this.usuario.departamento, this.mesSelecionado, this.anoSelecionado).subscribe(
            (data: ColaboradorComVariaveis[] | null) => {
                if (data && data.length > 0) {
                    data.sort((a, b) => a.colaboradoresDTO.nome.localeCompare(b.colaboradoresDTO.nome));

                    this.toastrService.success(`Lista atualizada com sucesso!`, "Sucesso", { duration: 5000 });
                    data.forEach(colaborador => {
                        this.calculosVariaveis(colaborador);
                        console.log(colaborador)
                    });
                    this.colaboradorComVariaveis = data.filter(colaborador => colaborador.colaboradoresDTO.status === true && colaborador.colaboradoresDTO.regimeContratacao != 'PJ');
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

    atualizarVariaveis(colaborador: ColaboradorComVariaveis) {

        colaborador.variaveisColaboradoresDTO.cpf = colaborador.colaboradoresDTO.cpf
        colaborador.variaveisColaboradoresDTO.mes = this.mesSelecionado
        colaborador.variaveisColaboradoresDTO.ano = this.anoSelecionado

        this.prvError = colaborador.variaveisColaboradoresDTO.prv > colaborador.combinacaoSalarialDTO.prv

        if (colaborador.variaveisColaboradoresDTO.ativo && !this.prvError) {

            this.variaveisColaboradoresService.updateVariaveisColaboradores(colaborador.variaveisColaboradoresDTO).subscribe(
                response => {
                    this.toastrService.success(`Atualizado com sucesso!`, "Sucesso", { duration: 5000 });
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
            if (this.prvError) {
                this.toastrService.warning(`PRV do colaborador ${colaborador.colaboradoresDTO.nome} tem que ser igual ou menor que R$${colaborador.combinacaoSalarialDTO.prv}`, "Erro", { duration: 5000 });
            } else {
                this.toastrService.warning('Falar com o DP, impossível aprovar colaborador inativo', "Erro", { duration: 5000 });
            }
        }

    }

    atualizarVariaveisComplementar(colaborador: ColaboradorComVariaveis) {

        if (colaborador.variaveisColaboradoresDTO.ativo) {

            this.variaveisColaboradoresService.updateVariaveisColaboradores(colaborador.variaveisColaboradoresDTO).subscribe(
                response => {
                    this.toastrService.success(`Atualizado com sucesso!`, "Sucesso", { duration: 5000 });
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
            this.toastrService.warning('Falar com o DP, impossível atualizar colaborador inativo', "Erro");
        }

    }

    aprovarVariaveis(colaborador: ColaboradorComVariaveis) {

        this.calculosVariaveis(colaborador);

        this.prvError = colaborador.variaveisColaboradoresDTO.prv > colaborador.combinacaoSalarialDTO.prv

        if (colaborador.variaveisColaboradoresDTO.ativo && !this.prvError) {

            colaborador.variaveisColaboradoresDTO.aprovadoGestor = true
            this.variaveisColaboradoresService.updateVariaveisColaboradores(colaborador.variaveisColaboradoresDTO).subscribe(
                response => {
                    this.toastrService.success(`Aprovado com sucesso!`, "Sucesso", { duration: 5000 });
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
            if (this.prvError) {
                this.toastrService.warning(`PRV do colaborador ${colaborador.colaboradoresDTO.nome} tem que ser igual ou menor que R$${colaborador.combinacaoSalarialDTO.prv}`, "Erro", { duration: 5000 });
            } else {
                this.toastrService.warning('Falar com o DP, impossível aprovar colaborador inativo', "Erro", { duration: 5000 });
            }
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
        this.loadUser();
        this.toggle();
    }

    toggle() {
        this.sidebarService.toggle();
    }

    buscarVariaveis() {
        this.getAllColaboradores();
    }

    limparVariaveis() {
        this.colaboradorComVariaveis = []
    }

    onToggleChange(checked: boolean, colaborador: ColaboradorComVariaveis) {

        if (checked) {
            colaborador.variaveisColaboradoresDTO.ativo = true
        } else {
            colaborador.variaveisColaboradoresDTO.ativo = false
        }

    }


}


