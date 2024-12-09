import { TestBed } from '@angular/core/testing';

import { VariaveisColaboradoresService } from './variaveis-colaboradores.service';

describe('VariaveisColaboradoresService', () => {
  let service: VariaveisColaboradoresService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VariaveisColaboradoresService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
