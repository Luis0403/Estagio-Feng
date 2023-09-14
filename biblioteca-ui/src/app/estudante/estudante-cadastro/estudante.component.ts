import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { ServicosEstudante } from '../servicos-estudante';
// import { Router } from '@angular/router';
// import { LoginComponent } from 'src/app/login/login/login.component';

@Component({
  selector: 'app-estudante',
  templateUrl: './estudante.component.html',
  styleUrls: ['./estudante.component.css']
})
export class EstudanteComponent implements OnInit {
  sexo=[
        {label:"MASCULINO", value:1},
          {label:"FEMININO", value:2}
        ];
  title='Nova';
  menuAjustado =true;

  estudante :Object={};  

  constructor(private estudanteServicos:ServicosEstudante, private rotear: Router) { 
    
  }


  
  aoClicar(exibindoMenu: boolean){
    this.menuAjustado = !this.menuAjustado;
  }

  salvar(form:NgForm){
    

    this.estudante={ "estudante":
                              { "nome":`${form.value.nome}`,
                                "sexo":`${form.value.sexo}`,
                                "numeroBi":`${form.value.numeroBI}`,
                                "dataNascimento":this.formatarData(form.value.dataNascimento),
                                "celular":form.value.celular,
                                "instituicao_de_ensino":`${form.value.instiuicaoEnsino}`,
                                "curso":`${form.value.curso}`
                              },
                      "utilizador":{ 
                                "nome":`${form.value.username}`,
                                 "email":`${form.value.email}`,
                                 "senha":`${form.value.password}`,
                                 "tipo":"ESTUDANTE"
                                    } 
                    };                 
                   

                    
    this.estudanteServicos.gravarEstudante(this.estudante).subscribe(
      resposta=> this.rotear.navigate(['/homepage']),
      erro=>console.log(erro)
    );
    
  }

  formatarData(date:Date){

    if((date.getMonth()+1)<10 && date.getDate()<10 ){
      return`${date.getFullYear()}-0${date.getMonth()+1}-0${date.getDate()}`
    }
    else 
      if(date.getDate()<10 &&  (date.getMonth()+1)>9){
        return `${date.getFullYear()}-${date.getMonth()+1}-0${date.getDate()}`
      }
      else
        if(date.getDate()>9 &&  (date.getMonth()+1)<10){
          return `${date.getFullYear()}-0${date.getMonth()+1}-${date.getDate()}`
        }
        else
            return `${date.getFullYear()}-${date.getMonth()+1}-${date.getDate()}`;

  }

  ngOnInit(): void {
  }

}
