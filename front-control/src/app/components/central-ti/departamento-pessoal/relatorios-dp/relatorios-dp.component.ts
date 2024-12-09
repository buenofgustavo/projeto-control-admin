import { Component } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Relatorios } from 'src/app/interface/relatorios';
import { Router } from '@angular/router';
import { NbToastrService } from '@nebular/theme';
import { MatDialog } from '@angular/material/dialog';
import { ModalRelatorioDpComponent } from './modais/modal-relatorio-dp/modal-relatorio-dp.component';

@Component({
  selector: 'app-relatorios-dp',
  templateUrl: './relatorios-dp.component.html',
  styleUrls: ['./relatorios-dp.component.scss']
})
export class RelatoriosDpComponent {
  relatorios: Relatorios[] = [
    { id: 1, relatorios: 'Relatório de variáveis dos colaboradores' },
    { id: 2, relatorios: 'Relatório de lançamentos dos colaboradores' },
    // Add more data as needed
  ];
  dataSource = new MatTableDataSource(this.relatorios);
  displayedColumns: string[] = ['relatorios', 'acoes'];
  loading: boolean = false;

  ngOnInit(): void {
    this.loadData();
  }

  loadData(): void {
    this.loading = true;
    setTimeout(() => {
      this.dataSource.data = this.relatorios;
      this.loading = false;
    }, 2000);
  }

  openDialog(relatorios: Relatorios) {
    const dialogRef = this.dialog.open(ModalRelatorioDpComponent, { data: { relatorios: relatorios } });
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result.relatorios}`);
    });
  }

  constructor(public dialog: MatDialog, private toastrService: NbToastrService, private router: Router) {
  }



}

