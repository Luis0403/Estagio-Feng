import { EstudanteModule } from './../estudante/estudante.module';
import { MenuLateralComponent } from './menu-lateral/menu-lateral.component';
import { PartilhadoModule } from './../partilhado/partilhado.module';
import { ProfessorModule } from './../professor/professor.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LivrosModule } from '../livros/livros.module';
import { LivroListarUserGestorComponent } from './livro-listar/livro-listarUserGestor.component';
import { LivroCadastrarUserGestorComponent } from './livro-cadastrar/livro-cadastrarUserGestor.component';
import { ProfessorCadastrarUserGestorComponent } from './professor-cadastrar/professor-cadastrarUserGestor.component';
import { ProfessorListarUserGestorComponent } from './professor-listar/professor-listarUserGestor.component';
import { EstudanteListarUserGestorComponent } from './estudante-listar/estudante-listarUserGestor.component';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    MenuLateralComponent,
    LivroListarUserGestorComponent,
    LivroCadastrarUserGestorComponent,
    ProfessorCadastrarUserGestorComponent,
    ProfessorListarUserGestorComponent,
    EstudanteListarUserGestorComponent
  ],
  imports: [
    CommonModule,
    LivrosModule,
    ProfessorModule,
    PartilhadoModule,
    EstudanteModule,
    RouterModule
  ],
  exports: [
    LivroListarUserGestorComponent,
    LivroCadastrarUserGestorComponent,
    ProfessorCadastrarUserGestorComponent,
    ProfessorListarUserGestorComponent,
    EstudanteListarUserGestorComponent
  ]
})
export class UserGestorModule { }
