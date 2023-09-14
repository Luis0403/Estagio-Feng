import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-gestor-livro-listar',
  templateUrl: './livro-listar.component.html',
  styleUrls: ['./livro-listar.component.css']
})
export class LivroListarUserGestorComponent {

  menuAjustado =true;

  aoClicar(exibindoMenu: boolean){
    this.menuAjustado = !this.menuAjustado;
  }

}
