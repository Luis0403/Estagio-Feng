import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-professor-cadastrar',
  templateUrl: './professor-cadastrar.component.html',
  styleUrls: ['./professor-cadastrar.component.css']
})
export class ProfessorCadastrarUserAdminComponent {

  menuAjustado =true;

  aoClicar(exibindoMenu: boolean){
    this.menuAjustado = !this.menuAjustado;
  }

}
