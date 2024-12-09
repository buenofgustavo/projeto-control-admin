import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { VariaveisColaboradores } from 'src/app/interface/variaveisColaboradores';
import { AuthserviceService } from '../authservice.service';
import { Observable } from 'rxjs';
import { API_CONFIG } from 'src/app/config/api.config';
import { LancamentoDp } from 'src/app/interface/lancamentoDp';

@Injectable({
  providedIn: 'root'
})
export class LancamentosDpService {

  private apiUrl = `${API_CONFIG.baseUrl}/importacao-dp`; // Use a URL da API a partir da configuração

  authToken: string | null;

  constructor(private http: HttpClient, private authService: AuthserviceService) {
    this.authToken = this.authService.extractAuthToken();
  }

  aprovarLancamentos(lancamentoDp: LancamentoDp): Observable<LancamentoDp> {
    if (!this.authToken) {
      throw new Error('Token JWT não encontrado, refaça o Login!');
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer${this.authToken}`);
    return this.http.post<LancamentoDp>(`${this.apiUrl}/aprovar`, lancamentoDp, { headers });
  }
}
