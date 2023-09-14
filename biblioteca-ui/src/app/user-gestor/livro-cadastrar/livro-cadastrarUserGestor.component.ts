import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-gestor-livro-cadastrar',
  templateUrl: './livro-cadastrar.component.html',
  styleUrls: ['./livro-cadastrar.component.css']
})
export class LivroCadastrarUserGestorComponent  {
  menuAjustado =true;

  aoClicar(exibindoMenu: boolean){
    this.menuAjustado = !this.menuAjustado;
  }

}
