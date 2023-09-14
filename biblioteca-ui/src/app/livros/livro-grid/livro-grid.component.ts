import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ConfirmationService, MessageService } from 'primeng/api';
import { ServicosService } from 'src/app/login/servicos.service';
import { LivroPerquisaComponent } from '../livro-perquisa/livro-perquisa.component';
import { LivrosService } from '../livros.service';

@Component({
  selector: 'app-livro-grid',
  templateUrl: './livro-grid.component.html',
  styleUrls: ['./livro-grid.component.css']
})
export class LivroGridComponent {
  @Input() livros: any[]=[];
  userPermissao=false;

  constructor(private livrosServicos: LivrosService, 
    private livroPesquisa: LivroPerquisaComponent,
    private loginSer: ServicosService,
    private confirmationService:ConfirmationService,
    private messageService: MessageService,
    private rota: Router
    ){

      if(this.loginSer.paylaodToken.roles[0]==='ROLE_ADMIN' || this.loginSer.paylaodToken.roles[0]==='ROLE_GESTOR'){
        this.userPermissao=true;
      }
   
  
  }

  showPop=false;

  OpenPop(){
    this.showPop=true;
  }

  ///metodo para download do livro
  downloadLivro(filename:string){

    if(this.loginSer.isLoggado){
      this.livrosServicos.download(filename).subscribe(
        event=>{
          
          this.livrosServicos.reportarProgresso(event,"download");
      },        
        (error:HttpErrorResponse)=>console.log(error)
        
      )
    }
    else
        this.loginSer.openBtnLogin=true;
 
  }

  confirmaDeletar(codigo:number, titulo:string){
    this.confirmationService.confirm({
      message: `Voce Deseja Deletar o livro: ${titulo}?`,
      header: 'Confirmacao de Deletar',
      icon: 'pi pi-info-circle',
      accept: () => {
          this.livroPesquisa.apagarLivro(codigo).subscribe(
            resposta=>{
              this.livroPesquisa.buscarLivros();
              this.messageService.add({severity:'info', summary:'Tarefa Confirmada', detail:'Livro Apagado Com Sucesso 😎',life:3500})
              },
            erro=>this.messageService.add({severity:'error', summary:'Tarefa Nao Confirmada', detail:'Nao foi possivel apagar o livro Porque Nao Existe na Base de Dados',life:3500})
          );
          
      },
      reject: (type: any) => {
          this.messageService.add({severity:'warn', summary:'Cancelado', detail:'Voce Cancelou',life:3500});       
      }
  });
   }
  

}
