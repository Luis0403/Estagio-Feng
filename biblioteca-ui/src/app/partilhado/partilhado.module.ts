import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MensagemErroComponent } from './mensagem-erro/mensagem-erro.component';
import {ButtonModule} from 'primeng/button';
import { HeaderComponent } from './header/header.component';
import { HeaderLogadoComponent } from './header-logado/header-logado.component';
import { FooterComponent } from './footer/footer.component';
import {  RouterLink, RouterModule } from '@angular/router';
import { HeaderhomeNaoLogadoComponent } from './headerhome-nao-logado/headerhome-nao-logado.component';
import { LoginModule } from '../login/login.module';
import { BooksGaleriaComponent } from './books-galeria/books-galeria.component';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { DialogModule } from 'primeng/dialog';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { DropdownModule } from 'primeng/dropdown';
import { PasswordModule } from 'primeng/password';
import { LivrosService } from '../livros/livros.service';
import { AppModule } from '../app.module';
import { TableModule } from 'primeng/table';

@NgModule({
  declarations: [
    MensagemErroComponent,
    HeaderComponent,
    HeaderLogadoComponent,
    FooterComponent,
    HeaderhomeNaoLogadoComponent,
    BooksGaleriaComponent
  ],
  imports: [
    CommonModule,
    ButtonModule,
    RouterModule,
    FormsModule,
    InputTextModule,
    ButtonModule,
    DialogModule,
    InputTextareaModule,
    DropdownModule,
    PasswordModule,
    TableModule
  ],
  exports: [
    MensagemErroComponent,
    HeaderComponent,
    HeaderLogadoComponent,
    FooterComponent,
    HeaderhomeNaoLogadoComponent,
    BooksGaleriaComponent,
    

  ],
  providers:[LivrosService]
})
export class PartilhadoModule { }
