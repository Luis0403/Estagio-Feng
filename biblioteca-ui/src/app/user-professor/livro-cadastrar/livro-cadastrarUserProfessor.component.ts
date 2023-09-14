import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-professor-livro-cadastrar',
  templateUrl: './livro-cadastrar.component.html',
  styleUrls: ['./livro-cadastrar.component.css']
})
export class LivroCadastrarUserProfessorComponent  {
  menuAjustado =true;

  aoClicar(exibindoMenu: boolean){
    this.menuAjustado = !this.menuAjustado;
  }

}
