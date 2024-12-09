import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from 'src/app/config/api.config';
import { CombinacaoSalarial } from 'src/app/interface/combinacaoSalarial';
import { AuthserviceService } from '../authservice.service';
import { VariaveisColaboradores } from 'src/app/interface/variaveisColaboradores';

@Injectable({
  providedIn: 'root'
})
export class VariaveisColaboradoresService {

  private apiUrl = `${API_CONFIG.baseUrl}/variaveis`; // Use a URL da API a partir da configuração

  authToken: string | null;

  constructor(private http: HttpClient, private authService: AuthserviceService) {
    this.authToken = this.authService.extractAuthToken();
  }

  cadastrarVariaveisColaboradores(VariaveisColaboradores: VariaveisColaboradores): Observable<VariaveisColaboradores> {
    if (!this.authToken) {
      throw new Error('Token JWT não encontrado, refaça o Login!');
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer${this.authToken}`);
    return this.http.post<VariaveisColaboradores>(`${this.apiUrl}/cadastrar`, VariaveisColaboradores, { headers });
  }

  updateVariaveisColaboradores(VariaveisColaboradores: VariaveisColaboradores): Observable<VariaveisColaboradores> {
    if (!this.authToken) {
      throw new Error('Token JWT não encontrado, refaça o Login!');
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer${this.authToken}`);
    return this.http.put<VariaveisColaboradores>(`${this.apiUrl}/update`, VariaveisColaboradores, { headers });
  }

  getVariaveis(cpf: string, mes:string, ano: number): Observable<VariaveisColaboradores> {
    if (!this.authToken) {
        throw new Error('Token JWT não encontrado, refaça o Login!');
    }

    const headers = new HttpHeaders().set('Authorization', `Bearer${this.authToken}`);

    return this.http.get<VariaveisColaboradores>(`${this.apiUrl}/listar/${cpf}/${mes}/${ano}`, { headers });
  }

}
