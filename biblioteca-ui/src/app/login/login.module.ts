import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { ButtonModule } from 'primeng/button';
import { FormsModule } from '@angular/forms';
import { NovaSenhaComponent } from './nova-senha/nova-senha.component';
import { DialogModule } from 'primeng/dialog';
import { RouterModule } from '@angular/router';
import { SendemailComponent } from './sendemail/sendemail.component';
import { PartilhadoModule } from '../partilhado/partilhado.module';
import { JwtHelperService, JWT_OPTIONS } from '@auth0/angular-jwt';
import { EstudanteGridComponent } from '../estudante/estudante-grid/estudante-grid.component';

@NgModule({
  declarations: [
    LoginComponent,
    NovaSenhaComponent,
    SendemailComponent
    
  ],
  imports: [
    CommonModule,
    InputTextModule,
    PasswordModule,
    ButtonModule,
    FormsModule,
    DialogModule,
    FormsModule,
    RouterModule,
    PartilhadoModule
  ],

  exports:[LoginComponent,NovaSenhaComponent],
 providers:[
    {provide: JWT_OPTIONS, useValue:JWT_OPTIONS},
    JwtHelperService, EstudanteGridComponent
  ]
})
export class LoginModule { }
