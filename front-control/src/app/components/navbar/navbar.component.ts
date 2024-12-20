import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NbMenuItem, NbMenuService, NbSidebarService, NbToastrService } from '@nebular/theme';
import { Usuario } from 'src/app/interface/usuario-interface';
import { AuthserviceService } from 'src/app/services/authservice.service';
import { UsuarioService } from 'src/app/services/usuario/usuario.service';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {

  toggle() {
    this.sidebarService.toggle();
  }

  items: NbMenuItem[] = [

    {
      title: 'Página Inicial',
      icon: 'home-outline',
      link: '/home',
      hidden: !this.authService.hasPermission(['ROLE_ADMIN', 'ROLE_DP', 'ROLE_COMPRAS', 'ROLE_TI']),
    },

    {
      title: 'Departamento Pessoal',
      icon: 'people-outline',
      hidden: !this.authService.hasPermission(['ROLE_ADMIN', 'ROLE_DP']),
      children: [
        { title: 'Colaboradores', icon: '', link: '/colaboradores-dp', },
        {
          title: 'Solicitações', icon: '', children: [
            { title: 'Nova Solicitação', icon: '', link: '/gestao-pessoal' },
            { title: 'Lista de Solicitações', icon: '', link: '/solicitacao-colaboradores-dp' },
          ]
        },
        {
          title: 'Variáveis', icon: '', children: [        
            { title: 'Pontuações PRV', icon: '', link: '/pontuacoes-colaboradores', },
            { title: 'Log Colaboradores', icon: '', link: '/logs-colaboradores', },
            { title: 'Importação de Lançamentos', icon: '', link: '/importacao-lancamentos' },
            { title: 'Relatórios', icon: '', link: '/relatorios-dp' }
          ]
        }

      ]
    },

    {
      title: 'Compras',
      icon: 'shopping-cart-outline',
      hidden: !this.authService.hasPermission(['ROLE_ADMIN', 'ROLE_COMPRAS']),
      children: [
        { title: 'Computadores', icon: '', link: '/computadores-compras', },
        { title: 'Colaboradores', icon: '', link: '/colaboradores-compras', },
        { title: 'Log Computadores', icon: '', link: '/log-computadores-compras', },
        { title: 'Relatórios', icon: '', link: '/relatorios-compras', },

        {
          title: 'Inventário',
          icon: '',
          children: [
            { title: 'Ar Condicionado', icon: '', link: '/gestao-ar-condicionado-ti' },
            { title: 'Armários', icon: '', link: '/gestao-armarios-ti' },
            { title: 'Bebedouros', icon: '', link: '/gestao-bebedouros-ti' },
            { title: 'Computadores  Inativos', icon: '', link: '/gestao-computador-ti' },
            { title: 'Cadeiras', icon: '', link: '/gestao-cadeiras-ti' },
            { title: 'CPD', icon: '', link: '/gestao-cpd-ti' },
            { title: 'Frigobar', icon: '', link: '/gestao-frigobar-ti' },
            { title: 'Impressoras', icon: '', link: '/gestao-impressoras-ti' },
            { title: 'Mesas', icon: '', link: '/gestao-mesas-ti' },
            { title: 'Micro-ondas', icon: '', link: '/gestao-microondas-ti' },
            { title: 'Monitores', icon: '', link: '/gestao-monitor-ti' },
            { title: 'Roteadores', icon: '', link: '/gestao-roteadores-ti' },
            { title: 'Transformadores', icon: '', link: '/gestao-transformadores-ti' },
            { title: 'Outros', icon: '', link: '/gestao-outros-ti' }
          ]
        },
      ]
    },

    {
      title: 'Departamento T.I',
      icon: 'hard-drive-outline',
      hidden: !this.authService.hasPermission(['ROLE_ADMIN', 'ROLE_TI']),
      children: [
        { title: 'Colaboradores', icon: '', link: '/colaboradores-ti', },
        { title: 'Lista de Solicitações', icon: '', link: '/solicitacoes-colaboradores-ti' },
        { title: 'Vinculação de Computador', icon: '', link: '/gestao-pessoal-ti' },
        { title: 'Registro Diário', icon: '', link: '/registro-diario' },

        {
          title: 'Computadores ', children: [
            // { title: 'Chamados', icon: '', link: '/chamados-ti' },
            { title: 'Computadores', icon: '', link: '/computadores-ti', },
            { title: 'Log Computadores', icon: '', link: '/log-computadores-ti', },
          ]
        },
      ]
    },

    // {
    //   title: 'Chamados',
    //   icon: 'clipboard-outline',
    //   children: [
    //     { title: 'Criar Chamado', icon: '', link: '/criar-chamados-geral' },
    //     { title: 'Visualizar Chamados', icon: '', link: '/visualizar-chamados-geral' },
    //   ]
    // },

    {
      title: 'Aprovação Gestores',
      icon: 'clipboard-outline',
      hidden: !this.authService.hasPermission(['ROLE_ADMIN', 'ROLE_GESTOR']),
      children: [
        { title: 'Aprovar Variáveis', icon: '', link: '/aprovacao-gestores' },
      ]
    },

    {
      title: 'Aprovação Pagamentos',
      icon: 'credit-card-outline',
      hidden: !this.authService.hasPermission(['ROLE_ADMIN']),
      children: [
        { title: 'Aprovar pagamentos', icon: '', link: '/aprovacao-pagamentos' },
      ]
    },

    {
      title: 'Cadastrar Usuários',
      icon: "person-add-outline",
      link: "/cadastrar-usuario",
      hidden: !this.authService.hasPermission(['ROLE_ADMIN', 'ROLE_TI']),
    },
  ];

  items2 = [
    { title: 'Logout', icon: '', link: '/', onClick: () => this.logout() },
  ];

  logout() {
    // Limpa o localStorage
    localStorage.clear();

    // Redireciona para a página inicial ou de login
    this.router.navigate(['/']);
  }

  ngOnInit() {
    this.loadUser();

    const reloaded = localStorage.getItem('reloaded');
    if (!reloaded) {
      localStorage.setItem('reloaded', 'true');
      location.reload(); // Recarrega a página
    } else {
      if (this.authService.hasPermission(['ROLE_GESTOR']) && !this.authService.hasPermission(['ROLE_ADMIN'])) {
        this.router.navigate(['/aprovacao-gestores']);
      }
    }

  }

  constructor(
    private usuarioService: UsuarioService,
    private toastrService: NbToastrService, private router: Router,
    private authService: AuthserviceService,
    private sidebarService: NbSidebarService,
    private menuService: NbMenuService

  ) {
    // FIZ DENTRO DO COMPONENTE
    // this.menuService.onItemClick().subscribe((event) => {
    //   if (event.item.title === 'Aprovar pagamentos' || event.item.title === 'Aprovar pagamentos' ) {
    //     this.toggle();
    //   }
    // });
  }

  usuario: Usuario = {
    name: '',
    login: '',
    password: '',
    role: '',
    departamento: ""
  }

  loadUser() {
    this.usuarioService.getUserByEmail().subscribe(
      (user: Usuario) => {
        this.usuario = user; // Armazene os dados do Pessoa na variável local
      },
      (error) => {
        console.error('Erro ao carregar dados do Pessoa:', error);
      }
    );
  }

}