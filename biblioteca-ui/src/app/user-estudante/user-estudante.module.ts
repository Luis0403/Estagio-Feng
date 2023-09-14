import { LivrosModule } from './../livros/livros.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LivroListarComponent } from './livro-listar/livro-listar.component';
import { PartilhadoModule } from '../partilhado/partilhado.module';
import { HomepageUserComponent } from './homepage-user/homepage-user.component';



@NgModule({
  declarations: [
    LivroListarComponent,
    HomepageUserComponent
  ],
  imports: [
    CommonModule,
    LivrosModule,
    PartilhadoModule
  ],
  exports: [
    LivroListarComponent,
    
  ]
})
export class UserEstudanteModule { }
