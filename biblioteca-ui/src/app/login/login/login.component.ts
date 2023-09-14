import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ServicosService } from '../servicos.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
 
  cont=0;
  resp=false;

  constructor( public servicos: ServicosService) { }

  ngOnInit(): void {
  }
 
  login(formulario: NgForm){
    this.servicos.login(formulario.value.username, formulario.value.password);
  }

  closeLogin(){
    this.servicos.openBtnLogin=false;
  }

  closePop(event:Event){
    console.log(event)
  }


}

