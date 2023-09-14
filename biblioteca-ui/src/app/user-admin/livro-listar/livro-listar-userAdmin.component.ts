import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-livro-listar',
  templateUrl: './livro-listar.component.html',
  styleUrls: ['./livro-listar.component.css']
})
export class LivroListarUserAdminComponent {

  menuAjustado =true;

  aoClicar(exibindoMenu: boolean){
    this.menuAjustado = !this.menuAjustado;
  }

}
