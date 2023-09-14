import { Component, OnInit } from '@angular/core';
import { ProfessorservicosService } from '../professorservicos.service';

@Component({
  selector: 'app-professor-pesquisa',
  templateUrl: './professor-pesquisa.component.html',
  styleUrls: ['./professor-pesquisa.component.css']
})
export class ProfessorPesquisaComponent{
  filtros=[
            {label:"Nome", value:1},
            {label:"Codigo", value:2},
            {label:"celular", value:3}];

  professor:any;

  constructor(private professorServicos: ProfessorservicosService){
    this.getProfessor()
  }

  getProfessor(){
    this.professorServicos.getProfessor().subscribe(
      resposta=>{this.professor=resposta},
      erro=>console.log(erro)
    )
  }
}
