import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-professor-listar',
  templateUrl: './professor-listar.component.html',
  styleUrls: ['./professor-listar.component.css']
})
export class ProfessorListarUserAdminComponent {

  menuAjustado =true;

  aoClicar(exibindoMenu: boolean){
    this.menuAjustado = !this.menuAjustado;
  }
}
