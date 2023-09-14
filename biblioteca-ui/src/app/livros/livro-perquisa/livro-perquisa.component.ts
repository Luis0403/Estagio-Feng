import { Component, OnInit } from '@angular/core';
import { isEmpty } from 'rxjs';
import { LivrosService } from '../livros.service';

@Component({
  selector: 'app-livro-perquisa',
  templateUrl: './livro-perquisa.component.html',
  styleUrls: ['./livro-perquisa.component.css']
})
export class LivroPerquisaComponent  {
  
 public livro: any;


  constructor(private livrosServicos: LivrosService){
    this.buscarLivros();
    
  }


  buscarLivros(){
    this.livrosServicos.buscarLivros().subscribe(
    resposta=>{

      this.livro=resposta
    },
    erro=>console.log(erro)
    );
  }

  apagarLivro(codigo: number){
    return this.livrosServicos.apagarLivroServicos(codigo);
  }


}
