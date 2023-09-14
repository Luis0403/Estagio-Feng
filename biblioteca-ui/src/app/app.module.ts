
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {  HttpClientModule} from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserAdminModule } from './user-admin/user-admin.module';

import { UserProfessorModule } from './user-professor/user-professor.module';
import { UserEstudanteModule } from './user-estudante/user-estudante.module';
import { UserGestorModule } from './user-gestor/user-gestor.module';
import { EstudanteModule } from './estudante/estudante.module';
import { LoginModule } from './login/login.module';
import { LivrosModule } from './livros/livros.module';
import { PartilhadoModule } from './partilhado/partilhado.module';
import { BibliotecarioModule } from './bibliotecario/bibliotecario.module';
import { LivroGridComponent } from './livros/livro-grid/livro-grid.component';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {ToastModule} from 'primeng/toast';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Interceptor } from './login/auth-interceptor.service';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    UserAdminModule,
    UserProfessorModule,
    UserGestorModule,
    UserEstudanteModule,
    LoginModule,
    EstudanteModule,
    HttpClientModule,
    LivrosModule,
    PartilhadoModule,
    BibliotecarioModule,
    ToastModule,
    ConfirmDialogModule,
    Interceptor
    
  ],
  providers: [HttpClientModule, LivroGridComponent, MessageService,ConfirmationService, AppComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
