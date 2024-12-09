import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RelatoriosDpComponent } from './relatorios-dp.component';

describe('RelatoriosDpComponent', () => {
  let component: RelatoriosDpComponent;
  let fixture: ComponentFixture<RelatoriosDpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RelatoriosDpComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RelatoriosDpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
