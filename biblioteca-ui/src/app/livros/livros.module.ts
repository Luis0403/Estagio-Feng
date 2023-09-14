import { PartilhadoModule } from './../partilhado/partilhado.module';
import { LivroPerquisaComponent } from './livro-perquisa/livro-perquisa.component';
import { LivroGridComponent } from './livro-grid/livro-grid.component';
import { LivroCadastroComponent } from './livro-cadastro/livro-cadastro.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {InputTextareaModule} from 'primeng/inputtextarea';
import {DropdownModule} from 'primeng/dropdown';
import { FormsModule } from '@angular/forms';
import {FileUploadModule} from 'primeng/fileupload';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {TooltipModule} from 'primeng/tooltip';
import {TableModule} from 'primeng/table';
import { LivroViewComponent } from './livro-view/livro-view.component';
import {DialogModule} from 'primeng/dialog';
import { Interceptor } from '../login/auth-interceptor.service';



@NgModule({
  declarations: [
    LivroCadastroComponent,
    LivroGridComponent,
    LivroPerquisaComponent,
    LivroViewComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    PartilhadoModule,
    InputTextareaModule,
    TableModule,
    DropdownModule,
    BrowserAnimationsModule,
    InputTextModule,
    ButtonModule,
    TooltipModule,
    DialogModule,
    FileUploadModule,
    Interceptor



  ],
  exports: [
    LivroCadastroComponent,
    LivroGridComponent,
    LivroPerquisaComponent,
    LivroViewComponent
  ]
})
export class LivrosModule { }
