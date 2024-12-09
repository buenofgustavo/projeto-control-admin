import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthserviceService } from '../../authservice.service';
import { API_CONFIG } from 'src/app/config/api.config';
import { DadosColaboradores } from 'src/app/interface/dados-colaboradores';
import { Observable } from 'rxjs';
import { ImportacaoLancametos } from 'src/app/interface/importacaoLancamentos';

@Injectable({
  providedIn: 'root'
})
export class ImportacaoLancamentosService {

  authToken: string | null;

  constructor(private http: HttpClient, private authService: AuthserviceService) {
    this.authToken = this.authService.extractAuthToken();

  }  

  private apiUrl = `${API_CONFIG.baseUrl}/importacao-dp`;

  importacaoLancamentos(mes: string, ano: number, arquivo: File): Observable<ImportacaoLancametos> {
    const formData: FormData = new FormData();
    formData.append('file', arquivo, arquivo.name) // Era 'file' alterei para files

    const headers = new HttpHeaders().set('Authorization', `Bearer${this.authToken}`);
    return this.http.post<ImportacaoLancametos>(`${this.apiUrl}/${mes}/${ano}`, formData, { headers });
  }

}
