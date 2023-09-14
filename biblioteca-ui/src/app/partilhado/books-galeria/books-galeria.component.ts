import { HttpClient } from '@angular/common/http';
import { compileNgModule } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { LivrosService } from 'src/app/livros/livros.service';
import { ServicosService } from 'src/app/login/servicos.service';

@Component({
  selector: 'app-books-galeria',
  templateUrl: './books-galeria.component.html',
  styleUrls: ['./books-galeria.component.css']
})
export class BooksGaleriaComponent implements OnInit {
  abirTextEmail=false;
  isfileFound=false;
  isfileNotFound=false;
  

  imagens=[
    {src:'/assets/img/Dark Web.JPG'},
    {src:'/assets/img/Python_for_Secret_Agents.JPG'},
    {src:'/assets/img/DeepLearningPractitionersApproach.JPG'}
  ];

  public cols = [
    { field: 'titulo', header: 'Titulo' },
    { field: 'autor', header: 'Autor' },
    { field: 'disciplina', header: 'Disciplina' },
    { field: 'editora', header: 'Editora' },
    { field:  'areaDeConhecimento',header:"Area de Conhecimento"}
  ]

  livros:any;

  logado:any//;=this.servicosLog.isLoggado;

  constructor(private rota: Router,private servicosLog: ServicosService, private livrosServicos:LivrosService,private httpClient: HttpClient) { 
    
    this.logado=this.servicosLog.isLoggado;
  }

  ngOnInit(): void {
  }

  enviarEmail(){
    this.abirTextEmail=true;
  }

  enviarEmailMessage(form:NgForm){
    this.abirTextEmail=false;
    const body=new FormData();
    body.append('email',form.value.email);
    body.append('emailText',form.value.emailText);
    body.append('subject',form.value.subject);

    this.httpClient.post('http://localhost:8080/biblioteca/sendEmail',body).subscribe(
      resposta=>console.log(resposta),
      erro=>console.log(erro)
    )
  }

  search(form:NgForm){
    this.livrosServicos.buscarLivrosByQuery(form.value.pesquisa).subscribe(
      resposta=>{
        if(JSON.stringify(resposta)==='[]'){
          this.isfileNotFound=true;
          this.isfileFound=false; 
        }
        else{
          this.livros=resposta;
          this.isfileFound=true;  
          this.isfileNotFound=false;        
        }
        
      },
      erro=>console.log(erro)
    )
  }

  cancelarEnviarEmail(){
    this.abirTextEmail=false;
  }

  downloadLivro(filename:string){
    if(!this.logado){
      this.servicosLog.openBtnLogin=true;
    }

    else{
        this.livrosServicos.download(filename).subscribe(
          event=>this.livrosServicos.reportarProgresso(event,"download"),
          erro=>console.log(erro)
        )
    }
        
  }
  lerLivro(filename:string){
    if(!this.logado){
      this.servicosLog.openBtnLogin=true;
    }
  } 

}
