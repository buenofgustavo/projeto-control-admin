import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { API_CONFIG } from 'src/app/config/api.config';
import { AuthserviceService } from '../authservice.service';

@Injectable({
  providedIn: 'root'
})
export class ExportRelatoriosDpService {

  private baseUrlService = `${API_CONFIG.baseUrl}/export/dp`; // Altere para a URL da sua API Spring Boot
  private baseUrlService2 = `${API_CONFIG.baseUrl}/export/dp-lancamento`; // Altere para a URL da sua API Spring Boot
  authToken: string | null;

  constructor(private http: HttpClient,
    private authService: AuthserviceService
  ) { 
    this.authToken = this.authService.extractAuthToken();
  }

  exportVariaveisDp(mes: string, ano: number): Observable<Blob> {

    const headers = new HttpHeaders().set('Authorization', `Bearer${this.authToken}`);

    // Aqui usamos `get` em vez de `put`, pois geralmente download de arquivos é feito via GET
    return this.http.get(`${this.baseUrlService}/${mes}/${ano}`,
      {
        headers,
        responseType: 'blob'
      }
    ).pipe(
      catchError(this.handleError)
    );
  }

  exportLancamentosDp(mes: string, ano: number): Observable<Blob> {

    const headers = new HttpHeaders().set('Authorization', `Bearer${this.authToken}`);

    // Aqui usamos `get` em vez de `put`, pois geralmente download de arquivos é feito via GET
    return this.http.get(`${this.baseUrlService2}/${mes}/${ano}`,
      {
        headers,
        responseType: 'blob'
      }
    ).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: any): Observable<any> {
    console.error('Erro na exportação para Excel:', error);
    throw error; // Aqui você pode tratar o erro conforme necessário
  }

}
