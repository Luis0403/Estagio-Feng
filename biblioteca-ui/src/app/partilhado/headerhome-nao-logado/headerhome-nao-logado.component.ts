import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServicosService } from 'src/app/login/servicos.service';

@Component({
  selector: 'app-headerhome-nao-logado',
  templateUrl: './headerhome-nao-logado.component.html',
  styleUrls: ['./headerhome-nao-logado.component.css']
})
export class HeaderhomeNaoLogadoComponent implements OnInit {

  constructor(private logSer: ServicosService) { }

  ngOnInit(): void {
  }


  LogIn(){
    this.logSer.openBtnLogin=true;
  }

  Signup(){
    // this.rotear.navigate(['/estudante/cadastro']);
  }
}
