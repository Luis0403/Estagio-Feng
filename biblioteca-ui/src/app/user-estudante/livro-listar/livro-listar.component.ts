import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-estudante-livro-listar',
  templateUrl: './livro-listar.component.html',
  styleUrls: ['./livro-listar.component.css']
})
export class LivroListarComponent {

  menuAjustado =true;

  aoClicar(exibindoMenu: boolean){
    this.menuAjustado = !this.menuAjustado;
  }

}
