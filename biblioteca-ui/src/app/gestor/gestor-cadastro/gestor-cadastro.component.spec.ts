import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestorCadastroComponent } from './gestor-cadastro.component';

describe('GestorCadastroComponent', () => {
  let component: GestorCadastroComponent;
  let fixture: ComponentFixture<GestorCadastroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestorCadastroComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GestorCadastroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
