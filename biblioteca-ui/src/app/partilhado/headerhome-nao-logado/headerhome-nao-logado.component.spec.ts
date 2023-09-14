import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderhomeNaoLogadoComponent } from './headerhome-nao-logado.component';

describe('HeaderhomeNaoLogadoComponent', () => {
  let component: HeaderhomeNaoLogadoComponent;
  let fixture: ComponentFixture<HeaderhomeNaoLogadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HeaderhomeNaoLogadoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HeaderhomeNaoLogadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
