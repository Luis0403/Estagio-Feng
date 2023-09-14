import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-gestor-estudante-listar',
  templateUrl: './estudante-listar.component.html',
  styleUrls: ['./estudante-listar.component.css']
})
export class EstudanteListarUserGestorComponent  {

  menuAjustado =true;

  aoClicar(exibindoMenu: boolean){
    this.menuAjustado = !this.menuAjustado;
  }

}
