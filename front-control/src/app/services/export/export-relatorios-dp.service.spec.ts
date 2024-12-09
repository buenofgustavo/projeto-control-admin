import { TestBed } from '@angular/core/testing';

import { ExportRelatoriosDpService } from './export-relatorios-dp.service';

describe('ExportRelatoriosDpService', () => {
  let service: ExportRelatoriosDpService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ExportRelatoriosDpService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
