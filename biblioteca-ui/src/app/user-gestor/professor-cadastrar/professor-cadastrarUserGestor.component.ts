import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-gestor-professor-cadastrar',
  templateUrl: './professor-cadastrar.component.html',
  styleUrls: ['./professor-cadastrar.component.css']
})
export class ProfessorCadastrarUserGestorComponent {

  menuAjustado =true;

  aoClicar(exibindoMenu: boolean){
    this.menuAjustado = !this.menuAjustado;
  }

}
