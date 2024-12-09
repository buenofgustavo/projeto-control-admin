import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PontuacoesColaboradoresComponent } from './pontuacoes-colaboradores.component';

describe('PontuacoesColaboradoresComponent', () => {
  let component: PontuacoesColaboradoresComponent;
  let fixture: ComponentFixture<PontuacoesColaboradoresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PontuacoesColaboradoresComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PontuacoesColaboradoresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
