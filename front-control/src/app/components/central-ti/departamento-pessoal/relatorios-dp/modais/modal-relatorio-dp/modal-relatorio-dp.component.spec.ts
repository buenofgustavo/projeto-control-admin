import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalRelatorioDpComponent } from './modal-relatorio-dp.component';

describe('ModalRelatorioDpComponent', () => {
  let component: ModalRelatorioDpComponent;
  let fixture: ComponentFixture<ModalRelatorioDpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalRelatorioDpComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalRelatorioDpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
