
import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';



import { Router } from '@angular/router';
import { EstudanteGridComponent } from '../estudante/estudante-grid/estudante-grid.component';
import { AppComponent } from '../app.component';


@Injectable({
  providedIn: 'root'
})
export class ServicosService {
  public paylaodToken:any;
  public  tokenNew: any;
  openBtnLogin=false;
  dados_invalidos=false;
  isLoggado=false;

   public roles = ['admin/livros/listar' ,'gestao/professores/listar', 'user_professor/add_ver_livros','/homepage/user-logado']

  constructor(private http: HttpClient, private jwt: JwtHelperService, private rotas: Router) { 
   
  }

  //autenticando o utilizador
  login(usuario:string, password:string){

    const urltoken='http://localhost:8080/biblioteca/login';

    const heades=new HttpHeaders();
    heades.append('Content-Type','application/x-www-form-urlencoded');
    heades.append('Content-Length','<calculated when request is sent>');
    heades.append('Host','<calculated when request is sent>');

    const body: FormData=new FormData();
    body.append('username',usuario);
    body.append('password',password)


     this.http.post(urltoken,body,{headers: heades})
      .subscribe(
        resposta=> {
          this.isLoggado=true;
          this.dados_invalidos=false;
          this.openBtnLogin=false;
          this.armazenarToken(Object.values(resposta)[0]);
          this.dirrecionarUuser();
        },
        err=>{
          if(err.status===401){
            this.dados_invalidos=true;
          }
        }
      );
      
  }


  // ROTEANDO O USER DE ACORDO COM AS SUAS PERMISSOES
  dirrecionarUuser() {
    this.openBtnLogin=false;
    switch(this.paylaodToken.roles[0]){
      case 'ROLE_ADMIN': 
        
        this.rotas.navigate([this.roles[0]]);
      break;
      case 'ROLE_GESTOR':  
        
        this.rotas.navigate([this.roles[1]]); 
      break;
      case 'ROLE_PROFESSOR': 
        
        this.rotas.navigate([this.roles[2]]); 
      break;
      case 'ROLE_ESTUDANTE':
        this.rotas.navigate([this.roles[3]]); 
      break;
    }
  }


  //ARMAZENAR O TOKEN NO STORAGE DO BROSWER
  private armazenarToken(token: any){
    this.paylaodToken=this.jwt.decodeToken(token);
    localStorage.setItem('token',token);
    
  }




}

 