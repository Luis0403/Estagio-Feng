import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { ServicosService } from 'src/app/login/servicos.service';

@Component({
  selector: 'app-header-logado',
  templateUrl: './header-logado.component.html',
  styleUrls: ['./header-logado.component.css']
})
export class HeaderLogadoComponent {

  @Output() menuClicado = new EventEmitter()
  exibindoMenu =false;
  username:any;
  trocarPass=false;
  dadosIncorrectos=false;
  isRole_Estudante=true;

  aoClicarMenu(){
    this.exibindoMenu = !this.exibindoMenu;

    this.menuClicado.emit(this.exibindoMenu)
  }

  constructor(
              private servicos: ServicosService, 
              private httpClient: HttpClient, 
              private rotear: Router)
  {
    this.username=servicos.paylaodToken.sub;

    if(this.servicos.paylaodToken.roles[0]!='ROLE_ESTUDANTE'){
      this.isRole_Estudante=false;
    }

  }

  sair(){
      this.httpClient.delete('http://localhost:8080/biblioteca/logout').subscribe(
        resposta=>{
          
          this.rotear.navigate(['/homepage']);
          localStorage.removeItem('token');
          this.servicos.paylaodToken=null;
          this.servicos.isLoggado=false;
        },
        erro=>console.log(erro)
      );
  }

  verificarPassword(password:string, novaPass: string){
   
    if(password!=novaPass){
      this.dadosIncorrectos=true;
    }
    else{
      const body: FormData=new FormData();
      body.append('username',this.username);
      body.append('nova_password',novaPass)
      this.httpClient.put('http://localhost:8080/biblioteca/change_passwordUpdate',body).subscribe(
        resposta=>{
          //TODO: mostrar mensagem de sucesso
          console.log(resposta)
          this.sair();
        },
        erro=>console.log(erro)
      );
    }
  }

  trocarPassword(open_close:string){
    if(open_close=='open'){
      this.trocarPass=true;
    }
    else
      if(open_close='close'){
        this.trocarPass=false;
      }
    
  }

 

}
