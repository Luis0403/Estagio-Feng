import { HttpClient, HttpEvent, HttpHeaders, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";


@Injectable({
    providedIn: 'root'
  })
  
export class ServicosEstudante{
    private heades:HttpHeaders | undefined;
    private SERVER_URL: any;
  
     constructor(private httpClient:HttpClient){
      this.SERVER_URL='http://localhost:8080/estudante/';
      this.heades=new HttpHeaders();
      this.heades.append('Content-Length','<calculated when request is sent>');
      this.heades.append('Host','<calculated when request is sent>');

     }

     buscarEstudantes(){
          
      return this.httpClient.get(this.SERVER_URL);
    }

    gravarEstudante(estudante:Object){
      
      return this.httpClient.post(this.SERVER_URL+"criarEstudante",estudante,{headers:this.heades});
    }

   
}