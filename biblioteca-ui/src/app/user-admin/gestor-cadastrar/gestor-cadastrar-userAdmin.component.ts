import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-gestor-cadastrar',
  templateUrl: './gestor-cadastrar.component.html',
  styleUrls: ['./gestor-cadastrar.component.css']
})
export class GestorCadastrarUserAdminComponent{

  menuAjustado =true;

  aoClicar(exibindoMenu: boolean){
    this.menuAjustado = !this.menuAjustado;
  }

}
