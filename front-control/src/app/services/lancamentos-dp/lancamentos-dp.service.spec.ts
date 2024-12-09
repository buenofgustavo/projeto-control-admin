import { TestBed } from '@angular/core/testing';

import { LancamentosDpService } from './lancamentos-dp.service';

describe('LancamentosDpService', () => {
  let service: LancamentosDpService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LancamentosDpService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
