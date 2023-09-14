import { HttpClient, HttpEvent, HttpHeaders, HttpEventType } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {saveAs} from 'file-saver';
import { ServicosService } from '../login/servicos.service';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';

@Injectable({
  providedIn: 'root'
})
export class LivrosService {
  fileStatus: { status: ''; requestType: ''; percent: 0; } | any;

 private SERVER_URL='http://localhost:8080/livro/'

  constructor(private httpClient: HttpClient, private loginServicos:ServicosService,private rota: Router, private appComponent: AppComponent){}

  buscarLivros(){

    //retorna livros criando por cada professor
    if(this.loginServicos.paylaodToken.roles[0]=='ROLE_PROFESSOR'){
     return this.httpClient.get(this.SERVER_URL+"por_user/"+this.loginServicos.paylaodToken.sub);
    }
    
    // retorna todos os livros
    return this.httpClient.get(this.SERVER_URL);
  }

  buscarLivrosByQuery(query_key: string){
    return this.httpClient.get(this.SERVER_URL+`${query_key}&${query_key}&${query_key}`);
  }

  //exclusao de livros
  apagarLivroServicos(codigo: number){
    return this.httpClient.delete(this.SERVER_URL+`${codigo}`);
  }

  // metodo para salvar o livro
  upload(formdata:FormData): Observable<HttpEvent<String>>{

    return this.httpClient.post<String>(this.SERVER_URL+'upload',formdata,
      {
        reportProgress:true,
        observe:'events'
      }
    );
  }

  saveBook(livro:any){
    return this.httpClient.post<String>(this.SERVER_URL,livro).subscribe(
        res=>{
          this.appComponent.mostarMessage("success","Sucesso","Livro Gurdado com Sucesso");
          if(this.loginServicos.paylaodToken.roles[0]=='ROLE_PROFESSOR'){
            this.rota.navigate(['professor/livros/listar/meus-livros']) 
          }
          else{
            const url=this.rota.url.substring(0,this.rota.url.length-6)+'s/listar'
            this.rota.navigate([''+url+''])  
          }            
        },
        err=>{ 
          this.appComponent.mostarMessage("error","Erro","O Livro Ja Existe");
        }
      );
  }

   // metodo para baixar o livro
   download(filename: string): Observable<HttpEvent<Blob>>{

   
    return this.httpClient.get(`${this.SERVER_URL}download?filename=${filename}`,
      {
        reportProgress:true,
        observe:'events',
        responseType:'blob'
      }
    );
  }

  // metodo para mostrar o progresso do download ou progresso
  public reportarProgresso(httpevent: HttpEvent<String> | HttpEvent<Blob>, down:string ):void{
    switch(httpevent.type){
      case HttpEventType.Sent: console.log("request sent"); break;
      case HttpEventType.UploadProgress:
        console.log(':UploadProgress ');
         //this.updateStatus(httpevent.loaded,Number(httpevent.total),'Uploading')
      ;break;
      case HttpEventType.DownloadProgress: 
       // this.updateStatus(httpevent.loaded,Number(httpevent.total),'downoading'); 
       console.log(':DownloadProgress ');
      break;
      case HttpEventType.ResponseHeader: console.log('cabecalho retornado:ResponseHeader ');
      break;
      case HttpEventType.DownloadProgress: alert("Download comecando....");break;
      case HttpEventType.Response:
        //logica do download
        if(down==='download'){
            saveAs(new File(
            [httpevent.body+''],
            httpevent.headers.get('File-Name')+'',
            {type: `${httpevent.headers.get('Content-Type')}; 
            charset=utf-8`}));
        }
      break;
    }
  }

  //mostra o estado do download
 private  updateStatus(loaded: number, total: number, requestType: string) {
  
  this.fileStatus.status='progress';
  this.fileStatus.requestType=requestType;
  this.fileStatus.percent=Math.round(100*loaded)/(total);
  
 }


}
