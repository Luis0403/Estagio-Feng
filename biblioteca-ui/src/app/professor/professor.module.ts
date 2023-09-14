import { ProfessorPesquisaComponent } from './professor-pesquisa/professor-pesquisa.component';
import { ProfessorGridComponent } from './professor-grid/professor-grid.component';
import { ProfessorCadastroComponent } from './professor-cadastro/professor-cadastro.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { TableModule } from 'primeng/table';
import { TooltipModule } from 'primeng/tooltip';
import { AppRoutingModule } from '../app-routing.module';
import { PartilhadoModule } from '../partilhado/partilhado.module';
import { Password, PasswordModule } from 'primeng/password';
import { Interceptor } from '../login/auth-interceptor.service';



@NgModule({
  declarations: [
    ProfessorCadastroComponent,
    ProfessorGridComponent,
    ProfessorPesquisaComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    DropdownModule,
    FormsModule,
    BrowserAnimationsModule,
    InputTextModule,
    ButtonModule,
    TooltipModule,
    TableModule,
    CalendarModule,
    PartilhadoModule,
    PasswordModule,
    // Interceptor
  ],
  exports: [
    ProfessorCadastroComponent,
    ProfessorGridComponent,
    ProfessorPesquisaComponent
  ],

})
export class ProfessorModule { }
