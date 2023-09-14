import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sendemail',
  templateUrl: './sendemail.component.html',
  styleUrls: ['./sendemail.component.css']
})
export class SendemailComponent implements OnInit {
  isEmailSent=false;
  showMessage=true;
  mail:string='';
  sendEmailBtn=true;

  constructor(private rota: Router, private httpClient: HttpClient) { }

  ngOnInit(): void {
  }

  EnviarEmail(email: string){

    this.isEmailSent=true;
    this.showMessage=false;
    this.mail=email;

    const body= new FormData();
    body.append("email", email)

    this.httpClient.post('http://localhost:8080/biblioteca/forgot_password',body).subscribe(
      resp=>console.log(resp),
      errr=>console.log(errr)
    )  }

  cancelarSendEmail(){
    this.rota.navigate(['/homepage']);
  }
}
