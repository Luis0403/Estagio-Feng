import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BibliotecarioService {

  constructor(private httpClient:HttpClient) { }

  criarBibliotecario(bibliotecario:Object){
   
    return this.httpClient.post('http://localhost:8080/gestor', bibliotecario);
  }

  getBibliotecario(){
    return this.httpClient.get('http://localhost:8080/gestor');
  }
}
