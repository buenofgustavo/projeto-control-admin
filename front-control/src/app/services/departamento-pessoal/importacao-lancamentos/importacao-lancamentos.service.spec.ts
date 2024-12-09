import { TestBed } from '@angular/core/testing';

import { ImportacaoLancamentosService } from './importacao-lancamentos.service';

describe('ImportacaoLancamentosService', () => {
  let service: ImportacaoLancamentosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ImportacaoLancamentosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
