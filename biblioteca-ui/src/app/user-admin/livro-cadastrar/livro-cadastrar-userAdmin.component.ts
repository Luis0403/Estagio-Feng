import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-livro-cadastrar',
  templateUrl: './livro-cadastrar.component.html',
  styleUrls: ['./livro-cadastrar.component.css']
})
export class LivroCadastrarUserAdminComponent  {
  menuAjustado =true;

  aoClicar(exibindoMenu: boolean){
    this.menuAjustado = !this.menuAjustado;
  }

}
