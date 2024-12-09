import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LogColaboradoresComponent } from './log-colaboradores.component';

describe('LogColaboradoresComponent', () => {
  let component: LogColaboradoresComponent;
  let fixture: ComponentFixture<LogColaboradoresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LogColaboradoresComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LogColaboradoresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
