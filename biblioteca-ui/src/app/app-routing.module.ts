import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BibliotecarioCadastroComponent } from './bibliotecario/bibliotecario-cadastro/bibliotecario-cadastro.component';
import { BibliotecarioListaComponent } from './bibliotecario/bibliotecario-lista/bibliotecario-lista.component';
import { EstudanteComponent } from './estudante/estudante-cadastro/estudante.component';
import { GuardarotasGuard } from './login/guardarotas.guard';
import { NovaSenhaComponent } from './login/nova-senha/nova-senha.component';
import { SendemailComponent } from './login/sendemail/sendemail.component';
import { BooksGaleriaComponent } from './partilhado/books-galeria/books-galeria.component';
import { EstudanteListarUserAdminComponent } from './user-admin/estudante-listar/estudante-listar-userAdmin.component';
import { GestorCadastrarUserAdminComponent } from './user-admin/gestor-cadastrar/gestor-cadastrar-userAdmin.component';
import { GestorListarUserAdminComponent } from './user-admin/gestor-listar/gestor-listar-userAdmin.component';
import { LivroCadastrarUserAdminComponent } from './user-admin/livro-cadastrar/livro-cadastrar-userAdmin.component';
import { LivroListarUserAdminComponent } from './user-admin/livro-listar/livro-listar-userAdmin.component';
import { ProfessorCadastrarUserAdminComponent } from './user-admin/professor-cadastrar/professor-cadastrar-userAdmin.component';
import { ProfessorListarUserAdminComponent } from './user-admin/professor-listar/professor-listar-userAdmin.component';
import { HomepageUserComponent } from './user-estudante/homepage-user/homepage-user.component';
import { EstudanteListarUserGestorComponent } from './user-gestor/estudante-listar/estudante-listarUserGestor.component';
import { LivroCadastrarUserGestorComponent } from './user-gestor/livro-cadastrar/livro-cadastrarUserGestor.component';
import { LivroListarUserGestorComponent } from './user-gestor/livro-listar/livro-listarUserGestor.component';
import { ProfessorCadastrarUserGestorComponent } from './user-gestor/professor-cadastrar/professor-cadastrarUserGestor.component';
import { ProfessorListarUserGestorComponent } from './user-gestor/professor-listar/professor-listarUserGestor.component';
import { LivroCadastrarUserProfessorComponent } from './user-professor/livro-cadastrar/livro-cadastrarUserProfessor.component';
import { LivroListarUserProfessorComponent } from './user-professor/livro-listar/livro-listarUserProfessor.component';

const routes: Routes = [
                     {path:'estudante/cadastro', component:EstudanteComponent},
                     {
                        path:'login/nova_senha', 
                        component:NovaSenhaComponent, 
                        // canActivate: [GuardarotasGuard],
                        // data:{ roles:['ROLE_ADMIN','ROLE_PROFESSOR','ROLE_ESTUDANTE','ROLE_GESTOR','ROLE_GESTOR']}
                     },
                     {path:'login/resetpass/send_email', component:SendemailComponent},
                     {
                        path:'admin/livros/listar', 
                        component:LivroListarUserAdminComponent,
                        canActivate: [GuardarotasGuard],
                        data:{ roles:['ROLE_ADMIN']}
                      },
                     { 
                        path:'gestao/professores/listar', 
                        component:ProfessorListarUserGestorComponent, 
                        canActivate: [GuardarotasGuard],
                        data:{ roles:['ROLE_GESTOR']}
                      },
                     {
                        path:'user_professor/add_ver_livros',
                        component:LivroListarUserProfessorComponent, 
                        canActivate: [GuardarotasGuard],
                        data:{ roles:['ROLE_PROFESSOR']}
                      },

                      //para user-admin
                     {
                        path:'admin/estudantes/listar', 
                        component: EstudanteListarUserAdminComponent,
                        canActivate: [GuardarotasGuard],
                        data:{ roles:['ROLE_ADMIN']}
                     },
                     
                     {
                        path:'admin/livro/criar', 
                        component: LivroCadastrarUserAdminComponent,
                        canActivate: [GuardarotasGuard],
                        data:{ roles:['ROLE_ADMIN']}
                     },
                     {
                        path:'admin/professores/listar',
                        component:  ProfessorListarUserAdminComponent, 
                        canActivate: [GuardarotasGuard],
                        data:{ roles:['ROLE_ADMIN']}
                     },
                     {
                        path:'admin/professor/criar', 
                        component: ProfessorCadastrarUserAdminComponent,
                        canActivate: [GuardarotasGuard],
                        data:{ roles:['ROLE_ADMIN']}
                     },
                       {
                        path:'admin/bibliotecario/criar', 
                        component: GestorCadastrarUserAdminComponent,
                        canActivate: [GuardarotasGuard],
                        data:{ roles:['ROLE_ADMIN']}
                     },
                     {
                        path:'admin/bibliotecario/listar', 
                        component: GestorListarUserAdminComponent,
                        canActivate: [GuardarotasGuard],
                        data:{ roles:['ROLE_ADMIN']}
                     },
                     
                     
                     //para user-gestor
                     {
                        path:'gestao/estudantes/listar',
                        component: EstudanteListarUserGestorComponent,
                        canActivate: [GuardarotasGuard],
                        data:{ roles:['ROLE_GESTOR']}
                     },
                     {
                        path:'gestao/livros/listar', 
                        component: LivroListarUserGestorComponent,
                        canActivate: [GuardarotasGuard],
                        data:{ roles:['ROLE_GESTOR']}
                     },
                     {
                        path:'gestao/livro/criar', 
                        component: LivroCadastrarUserGestorComponent,
                        canActivate: [GuardarotasGuard],
                        data:{ roles:['ROLE_GESTOR']}
                     },
                     {
                        path:'gestao/professor/criar',
                        component: ProfessorCadastrarUserGestorComponent,
                        canActivate: [GuardarotasGuard],
                        data:{ roles:['ROLE_GESTOR']}
                     },

                     //para user-professor
                     {  
                        path:'professor/livros/listar/meus-livros',
                        component: LivroListarUserProfessorComponent,
                        canActivate: [GuardarotasGuard],
                        data:{ roles:['ROLE_PROFESSOR']}
                     },
                     {
                        path:'professor/livro/criar',
                        component: LivroCadastrarUserProfessorComponent,
                        canActivate: [GuardarotasGuard],
                        data:{ roles:['ROLE_PROFESSOR']}
                     },
                     {
                        path:'homepage',
                        component: BooksGaleriaComponent
                     },
                     {
                        path:'homepage/user-logado',
                        component: HomepageUserComponent,
                        canActivate: [GuardarotasGuard],
                        data:{ roles:['ROLE_ESTUDANTE']}
                        
                     }
];

@NgModule({
 imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
