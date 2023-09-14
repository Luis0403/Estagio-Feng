import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestorPesquisaComponent } from './gestor-pesquisa.component';

describe('GestorPesquisaComponent', () => {
  let component: GestorPesquisaComponent;
  let fixture: ComponentFixture<GestorPesquisaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestorPesquisaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GestorPesquisaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
