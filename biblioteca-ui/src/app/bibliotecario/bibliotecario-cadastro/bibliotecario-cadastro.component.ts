import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { AppComponent } from 'src/app/app.component';
import { BibliotecarioService } from '../bibliotecario.service';

@Component({
  selector: 'app-bibliotecario-cadastro',
  templateUrl: './bibliotecario-cadastro.component.html',
  styleUrls: ['./bibliotecario-cadastro.component.css']
})
export class BibliotecarioCadastroComponent implements OnInit {
  genero=[{label:"MASCULINO", value:1},
  {label:"FEMININO", value:2}];

  constructor(private bibliotecarioServicos: BibliotecarioService, private rota: Router,private messageService: MessageService) { }

  ngOnInit(): void {
  }

  criarBibliotecario(form:NgForm){  

    const 
    bibliotecario= {
        
      "gestor":{
         "nome": `${form.value.nome}`,
         "sexo": `${this.genero[form.value.sexo-1].label}`,
         "numeroBi": `${form.value.numeroBi}`,
         "dataNascimento":this.formatarData(form.value.dataNascimento),
         "celular":form.value.celular
        },
         "utilizador": {
           "nome": `${form.value.username}`,
           "email": `${form.value.email}`,
           "senha": `${form.value.password}`,
           "tipo": "GESTOR"
        }

    }
    
    this.bibliotecarioServicos.criarBibliotecario(bibliotecario).subscribe(
      resposta=>{
        this.messageService.add({severity:'success', summary:'Sucesso', detail:'Bibliotecario Criado com Sucesso',life:3500})
        const url=this.rota.url.substring(0,this.rota.url.length-5)+'/listar'
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
