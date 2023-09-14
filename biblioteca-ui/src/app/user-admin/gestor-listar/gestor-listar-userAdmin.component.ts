import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-gestor-listar',
  templateUrl: './gestor-listar.component.html',
  styleUrls: ['./gestor-listar.component.css']
})
export class GestorListarUserAdminComponent {

  menuAjustado =true;

  aoClicar(exibindoMenu: boolean){
    this.menuAjustado = !this.menuAjustado;
  }

}
