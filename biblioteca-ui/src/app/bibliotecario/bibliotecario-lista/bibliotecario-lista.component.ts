import { Component, OnInit } from '@angular/core';
import { BibliotecarioService } from '../bibliotecario.service';

@Component({
  selector: 'app-bibliotecario-lista',
  templateUrl: './bibliotecario-lista.component.html',
  styleUrls: ['./bibliotecario-lista.component.css']
})
export class BibliotecarioListaComponent implements OnInit {
  public cols = [
    { field: 'codigo', header: 'Codigo' },
    { field: 'nome', header: 'Nome' },
    { field: 'sexo', header: 'Sexo' },
    { field: 'celular', header: 'Celular'}
  ]

  public Bibliotecario:any;
  constructor(private bibliotecarioServicos: BibliotecarioService) { 
    this.getBibliotecarios()
  }

  ngOnInit(): void {
  }

  getBibliotecarios(){
    this.bibliotecarioServicos.getBibliotecario().subscribe(
      
      resposta=>this.Bibliotecario=resposta,
      erro=>console.log(erro)
    );
  }

}
