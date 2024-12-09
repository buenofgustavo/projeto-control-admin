import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImportacaoLancamentosComponent } from './importacao-lancamentos.component';

describe('ImportacaoLancamentosComponent', () => {
  let component: ImportacaoLancamentosComponent;
  let fixture: ComponentFixture<ImportacaoLancamentosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ImportacaoLancamentosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ImportacaoLancamentosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
