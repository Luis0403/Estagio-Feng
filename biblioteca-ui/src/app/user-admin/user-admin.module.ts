import { EstudanteModule } from './../estudante/estudante.module';
import { GestorModule } from './../gestor/gestor.module';
import { ProfessorModule } from './../professor/professor.module';

import { LivrosModule } from './../livros/livros.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PartilhadoModule } from '../partilhado/partilhado.module';
import { MenuLateralComponent } from './menu-lateral/menu-lateral.component';
import { LivroListarUserAdminComponent } from './livro-listar/livro-listar-userAdmin.component';
import { LivroCadastrarUserAdminComponent } from './livro-cadastrar/livro-cadastrar-userAdmin.component';
import { ProfessorCadastrarUserAdminComponent } from './professor-cadastrar/professor-cadastrar-userAdmin.component';
import { ProfessorListarUserAdminComponent } from './professor-listar/professor-listar-userAdmin.component';
import { GestorCadastrarUserAdminComponent } from './gestor-cadastrar/gestor-cadastrar-userAdmin.component';
import { GestorListarUserAdminComponent } from './gestor-listar/gestor-listar-userAdmin.component';
import { EstudanteListarUserAdminComponent } from './estudante-listar/estudante-listar-userAdmin.component';
import { RouterModule } from '@angular/router';
import { BibliotecarioModule } from '../bibliotecario/bibliotecario.module';


@NgModule({
  declarations: [
    MenuLateralComponent,
    LivroListarUserAdminComponent,
    LivroCadastrarUserAdminComponent,
    ProfessorCadastrarUserAdminComponent,
    ProfessorListarUserAdminComponent,
    GestorCadastrarUserAdminComponent,
    GestorListarUserAdminComponent,
    EstudanteListarUserAdminComponent
  ],
  imports: [
    CommonModule,
    LivrosModule,
    ProfessorModule,
    GestorModule,
    EstudanteModule,
    PartilhadoModule,
    RouterModule,
    BibliotecarioModule
  ],
  exports: [
    LivroListarUserAdminComponent,
    LivroCadastrarUserAdminComponent,
    ProfessorCadastrarUserAdminComponent,
    ProfessorListarUserAdminComponent,
    GestorCadastrarUserAdminComponent,
    GestorListarUserAdminComponent,
    EstudanteListarUserAdminComponent
  ],
})
export class UserAdminModule { }
