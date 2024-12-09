import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AprovacaoPagamentosComponent } from './aprovacao-pagamentos.component';

describe('AprovacaoPagamentosComponent', () => {
  let component: AprovacaoPagamentosComponent;
  let fixture: ComponentFixture<AprovacaoPagamentosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AprovacaoPagamentosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AprovacaoPagamentosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
