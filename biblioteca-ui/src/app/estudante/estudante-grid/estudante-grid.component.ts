import { HttpEvent, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ServicosEstudante } from '../servicos-estudante';

@Component({
  selector: 'app-estudante-grid',
  templateUrl: './estudante-grid.component.html',
  styleUrls: ['./estudante-grid.component.css']
})
export class EstudanteGridComponent {
   public estudante:any;
   public estudanteFiltro:any;
   public menuAjustado =true;

 public cols = [
                { field: 'codigo', header: 'Codigo' },
                { field: 'nome', header: 'Nome' },
                { field: 'curso', header: 'Curso' },
                { field: 'celular', header: 'Celular' },
                { field:  'instituicao_de_ensino',header:"Instituicao"},
                { field:  'numeroBi',header:"Numero de BI"}
 ]


  constructor( private servicosEstudante: ServicosEstudante) { 
   this.getEstudantes();
  }


  aoClicar(exibindoMenu: boolean){
       this.menuAjustado = !this.menuAjustado;
  }

  public getEstudantes(){
      this.servicosEstudante.buscarEstudantes().subscribe

        (
          resposta=>{
            this.estudante=resposta;
         },
         erro=>console.log(erro)
        );

  }

  public filtroEstudante(form: NgForm){
    
    for(let estudantes of this.estudante){
        if(estudantes.nome===form.value.Utilizador.nome || estudantes.email===form.value.email){
          this.estudanteFiltro.push(estudantes);
        }
    }
  }

}


