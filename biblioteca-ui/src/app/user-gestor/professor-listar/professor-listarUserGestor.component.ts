import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-gestor-professor-listar',
  templateUrl: './professor-listar.component.html',
  styleUrls: ['./professor-listar.component.css']
})
export class ProfessorListarUserGestorComponent {

  menuAjustado =true;

  aoClicar(exibindoMenu: boolean){
    this.menuAjustado = !this.menuAjustado;
  }
}
