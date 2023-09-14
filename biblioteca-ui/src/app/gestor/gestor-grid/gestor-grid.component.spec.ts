import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestorGridComponent } from './gestor-grid.component';

describe('GestorGridComponent', () => {
  let component: GestorGridComponent;
  let fixture: ComponentFixture<GestorGridComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestorGridComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GestorGridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
