import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { NbToastrService } from '@nebular/theme';
import { LogColaboradores } from 'src/app/interface/logColaboradores';
import { ColaboradoresService } from 'src/app/services/departamento-pessoal/colaboradores/colaboradores.service';

@Component({
  selector: 'app-log-colaboradores',
  templateUrl: './log-colaboradores.component.html',
  styleUrls: ['./log-colaboradores.component.scss']
})
export class LogColaboradoresComponent {
  logColaboradores: LogColaboradores[] = [];
  dataSource = new MatTableDataSource<LogColaboradores>(this.logColaboradores);
  displayedColumns: string[] = [ 'dpid', 'tipo', 'nomeColaborador', 'mensagem', 'usuario', 'datahora'];

  concluido: boolean = false;
  makro: boolean = false;


  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  constructor(public dialog: MatDialog, private toastrService: NbToastrService,
    private router: Router, private colaboradoresService: ColaboradoresService,
  ) {
    this.getLog();
  }

  loading: boolean = true;
  getLog() {
    this.colaboradoresService.getAllLogColaboradores().subscribe(
      (data: LogColaboradores[] | null) => {
        try {
          if (data) {
            this.logColaboradores = data
            this.logColaboradores.reverse();
            this.dataSource.data = this.logColaboradores;
          } else {
            throw new Error('Array de logs é nulo.');
          }
        } catch (error) {
          console.log('Erro ao filtrar logs:', error);
          this.toastrService.danger('Erro ao filtrar logs.', 'Erro');
        } finally {
          this.loading = false; // Finaliza o estado de carregamento após tentar obter e filtrar os dados
        }
      },
      (error) => {
        console.log('Erro ao obter logs:', error);
        if (error.status === 403) {
          setTimeout(() => {
            location.reload();
          }, 2000);
        } else {
          this.toastrService.danger('Erro ao obter logs.', 'Erro');
        }
        this.loading = false;
      }
    );
  }

  selectedFilter: string = '';
  filterValue: string = '';

  ngOnInit() {
    // Recupera os valores do filtro do localStorage
    const storedSelectedFilter = localStorage.getItem('selectedFilter-log-dp');
    const storedFilterValue = localStorage.getItem('filterValue-log-dp');
    const storedTermo = localStorage.getItem('termo-log-dp');
    const storedConcluido = localStorage.getItem('concluido-log-dp');

    if (storedSelectedFilter) {
      this.selectedFilter = storedSelectedFilter;
    }

    if (storedFilterValue) {
      this.filterValue = storedFilterValue;
      this.applyFilterWithValue(storedFilterValue);
    }

    if (storedConcluido !== null) {
      this.concluido = storedConcluido === 'true';
    }
  }

  applyFilter(event: any) {
    const filterValue = (event.target as HTMLInputElement).value.trim().toLowerCase();
    this.applyFilterWithValue(filterValue);

    // Salva os valores no localStorage
    localStorage.setItem('selectedFilter-log-dp', this.selectedFilter!);
    localStorage.setItem('filterValue-log-dp', filterValue);
  }

  applyFilterWithValue(filterValue: string) {
    const normalizedFilterValue = this.normalizeString(filterValue.toLowerCase());

    // Verifica se há um filtro selecionado
    if (this.selectedFilter) {
      // Aplica o filtro no campo selecionado
      this.dataSource.filter = normalizedFilterValue;
      this.dataSource.filterPredicate = (data: any, filter: string) => {
        const normalizedDataValue = this.getNormalizedFieldValue(data);
        return normalizedDataValue.includes(filter);
      };
    } else {
      // Se nenhum filtro estiver selecionado, limpa o filtro
      this.dataSource.filter = '';
    }
  }

  getNormalizedFieldValue(data: any): string {
    switch (this.selectedFilter) {
      case 'nome':
        return this.normalizeString(data.nomeColaborador.toLowerCase());
      case 'tipo':
        return this.normalizeString(data.tipo.toLowerCase());
      case 'cpf':
        return this.normalizeString(data.cpfColaborador.toLowerCase());
      case 'usuario':
        return this.normalizeString(data.usuario.toLowerCase());
      case 'mesAno':
        return this.normalizeString(data.mesAno.toLowerCase());
      case 'dpid':
        return this.normalizeString(data.dpid.toLowerCase());
      default:
        return ''; // Retorna uma string vazia se nenhum campo for selecionado
    }
  }

  normalizeString(str: string): string {
    return str.normalize("NFD").replace(/[\u0300-\u036f]/g, "");
  }

}
