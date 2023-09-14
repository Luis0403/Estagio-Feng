import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { ProfessorservicosService } from '../professorservicos.service';

@Component({
  selector: 'app-professor-cadastro',
  templateUrl: './professor-cadastro.component.html',
  styleUrls: ['./professor-cadastro.component.css']
})
export class ProfessorCadastroComponent{
  genero=[{label:"MASCULINO", value:1},
  {label:"FEMININO", value:2}];

  constructor(private professorServicos: ProfessorservicosService, private rota: Router, private messageService: MessageService){}

  criarProfessor(form:NgForm){
       
    const 
    professor= {
        
      "professor":{
         "nome": `${form.value.nome}`,
         "sexo": `${form.value.sexo}`,
         "numeroBi": `${form.value.numeroBi}`,
         "dataNascimento":this.formatarData(form.value.dataNascimento) ,
         "celular":form.value.celular,
         "instituicaoDeEnsino":`${form.value.instiuicaoEnsino}`,
         "areaDeEnsino": `${form.value.areaEnsino}`
        },
         "utilizador": {
           "nome": `${form.value.username}`,
           "email": `${form.value.email}`,
           "senha": `${form.value.password}`,
           "tipo": "PROFESSOR"
        }

    }

    console.log(professor);
    
    this.professorServicos.criarProfessor(professor).subscribe(
      resposta=>{
        this.messageService.add({severity:'success', summary:'Sucesso', detail:'Professor Criado com Sucesso',life:3500})
        const url=this.rota.url.substring(0,this.rota.url.length-6)+'es/listar'
        this.rota.navigate([''+url+''])  
      },
      erro=>this.messageService.add({severity:'error', summary:'Erro', detail:'O Email ou Username Ja Existe ',life:3500})
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
}
