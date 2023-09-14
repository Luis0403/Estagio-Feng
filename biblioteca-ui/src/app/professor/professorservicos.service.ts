import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class ProfessorservicosService {

  constructor(private httpClient:HttpClient) { }

  criarProfessor(professor:Object){
    return this.httpClient.post('http://localhost:8080/professor',professor);
  }

  getProfessor(){
    return this.httpClient.get('http://localhost:8080/professor');
  }
}
