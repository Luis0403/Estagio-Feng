import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Biblioteca';
 
  constructor(private rota:Router,private messageService: MessageService){
   rota.navigate(['/homepage']);
  }

  mostarMessage(severityText:string, summaryText:string, detailText:string) {
    this.messageService.add({severity:severityText, summary: summaryText, detail: detailText,life:3500});
 }



}
