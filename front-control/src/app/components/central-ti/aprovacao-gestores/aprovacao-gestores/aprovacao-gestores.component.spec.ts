import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AprovacaoGestoresComponent } from './aprovacao-gestores.component';

describe('AprovacaoGestoresComponent', () => {
  let component: AprovacaoGestoresComponent;
  let fixture: ComponentFixture<AprovacaoGestoresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AprovacaoGestoresComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AprovacaoGestoresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
