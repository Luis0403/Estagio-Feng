import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-estudante-listar',
  templateUrl: './estudante-listar.component.html',
  styleUrls: ['./estudante-listar.component.css']
})
export class EstudanteListarUserAdminComponent  {

  menuAjustado =true;

  aoClicar(exibindoMenu: boolean){
    this.menuAjustado = !this.menuAjustado;
  }

}
