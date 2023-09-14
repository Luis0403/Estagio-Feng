import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuLateralComponent } from './menu-lateral/menu-lateral.component';
import { LivroListarUserProfessorComponent } from './livro-listar/livro-listarUserProfessor.component';
import { LivroCadastrarUserProfessorComponent } from './livro-cadastrar/livro-cadastrarUserProfessor.component';
import { LivrosModule } from '../livros/livros.module';
import { PartilhadoModule } from '../partilhado/partilhado.module';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    MenuLateralComponent,
    LivroListarUserProfessorComponent,
    LivroCadastrarUserProfessorComponent,
  ],
  imports: [
    CommonModule,
    LivrosModule,
    PartilhadoModule,
    RouterModule
  ],
  exports: [
    LivroListarUserProfessorComponent,
    LivroCadastrarUserProfessorComponent,
  ]
})
export class UserProfessorModule { }
