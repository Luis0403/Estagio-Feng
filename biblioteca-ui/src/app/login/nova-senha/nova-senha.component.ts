import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nova-senha',
  templateUrl: './nova-senha.component.html',
  styleUrls: ['./nova-senha.component.css']
})
export class NovaSenhaComponent implements OnInit {
  public dadosIncorrectos=false;
  constructor() { }

  ngOnInit(): void {
  }

  trocarPassword(password:string, novaPass: string){
    alert('jahhhh')
    if(password!=novaPass){
      this.dadosIncorrectos=true;
    }
  }


}
